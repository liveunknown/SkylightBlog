package com.skylight.blog.controller;

import com.skylight.blog.mapper.UserMapper;
import com.skylight.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
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
    public boolean imageUpload(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("11111111: 进入上传图片方法了"+(file!=null));
        System.out.println("11111111: 输出file"+file);
        //存入数据库的文件URL
        String accessUrl = "http://fkyy.xmu.edu.cn/resources/fkyyRes/";
        String path="";
        //判断file数组不能为空并且长度大于0
        if(file!=null){
            //保存文件
            if (!file.isEmpty()) {
                try {
                    // 文件保存路径(服务器存放文件的地址)
                    String pathRoot =  "C:\\Users\\Air\\Desktop\\images\\";
                    //String pathRoot = "/usr/local/apache-tomcat-9.0.7/fkyyRes/";
                    System.out.println("111111: "+pathRoot);

                    String contentType=file.getContentType();
                    //获得文件后缀名称
                    String imageName=contentType.substring(contentType.indexOf("/")+1);
                    String uuid = UUID.randomUUID().toString().replaceAll("-","");
                    path=uuid+"."+imageName;
                    //转存文件到服务器上
                    System.out.println("111111: "+path);

                    file.transferTo(new File(pathRoot+path));
                    /*
                    BigInteger imageId = generateID.getID();
                    AttractionImage attractionImage = new AttractionImage();
                    attractionImage.setId(imageId);
                    attractionImage.setAttractionId(attractionId);
                    attractionImage.setImageUrl(accessUrl+path);
                    sceneryService.addImage(attractionImage);
*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                return false;
            }

        }
        else
        {
            return false;
        }

        return true;
    }
}