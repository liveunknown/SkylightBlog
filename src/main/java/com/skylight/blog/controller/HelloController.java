package com.skylight.blog.controller;

import com.skylight.blog.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 测试控制器
 */
@Controller
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        logger.info("HelloController里的日志输出！info");
        return "Hello MyBlog!";
    }

    @RequestMapping("/myError")
    public String test(){
        return "error/404";
    }

    @RequestMapping("/wrong")
    public int zero(){
        return 1/0;
    }

    @RequestMapping("/exception")
    public String exception() throws Exception {
        logger.info("访问/hello时抛出了异常！");
        throw new Exception("访问/hello时抛出了异常！");
    }

    @RequestMapping("/myException")
    public String myException() throws MyException {
        logger.info("访问/hello时抛出了自定义异常！");
        throw new MyException("666","这里是自定义异常！");
    }

    @RequestMapping(value="/imageUpload",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> imageUpload(@RequestParam(value = "image", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response)
    {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            request.setCharacterEncoding("utf-8");
            //设置返回头后页面才能获取返回url
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            System.out.println("进入上传图片方法了: "+(file!=null));
            System.out.println("输出file: "+file);

            // 文件保存路径(服务器存放文件的地址)
            //     /usr/local/MyBlog/images
            String Root = "C:\\Users\\Air\\Desktop\\images\\";
            //String Root = "/usr/local/MyBlog/images/";

            String path = "";
            String Url = "http://localhost:8080/image/";
            //String Url = "http://www.baidurex.com/image/";

            System.out.println("Root: "+Root);
            String contentType=file.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            path=uuid+"."+imageName;
            //转存文件到服务器上
            System.out.println("path: "+path);
            file.transferTo(new File(Root+path));

            resultMap.put("success", 1);
            resultMap.put("message", "上传成功");
            //resultMap.put("url",Root+path);
            resultMap.put("url",Url+path);

        } catch (Exception e) {
            try {
                response.getWriter().write( "{\"success\":0}" );
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return resultMap;
    }
}