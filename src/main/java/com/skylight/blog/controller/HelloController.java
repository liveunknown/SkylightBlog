package com.skylight.blog.controller;

import com.skylight.blog.mapper.UserMapper;
import com.skylight.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 测试控制器
 */
@RestController
public class HelloController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello MyBlog!";
    }

    @RequestMapping("/user")
    @ResponseBody
    public User getUser(int id) {
        return userMapper.GetUserById(id);
    }

    @RequestMapping("/users")
    @ResponseBody
    public List<User> getUsers() {
        return userMapper.GetUsersByPageNumber();
    }

    //UploadImage
    @RequestMapping(value="/uploadImage",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> imageUpload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response)
    {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            request.setCharacterEncoding("utf-8");
            //设置返回头后页面才能获取返回url
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            System.out.println("进入上传图片方法了: "+(file!=null));
            System.out.println("输出file: "+file);

            // 文件保存路径(服务器存放文件的地址)
            String Root = "C:\\Users\\Air\\Desktop\\images\\";
            String path = "";
            String Url = "http://localhost:8080/image/";

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