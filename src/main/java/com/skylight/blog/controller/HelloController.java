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
}