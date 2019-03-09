package com.skylight.blog.controller;

import com.skylight.blog.mapper.SummaryMapper;
import com.skylight.blog.mapper.UserMapper;
import com.skylight.blog.model.ArticleSummary;
import com.skylight.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试控制器
 */
@RestController
public class HelloController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SummaryMapper summaryMapper;


    @RequestMapping("/hello")
    public String hello() {
        return "Hello MyBlog!";
    }

    @RequestMapping("/user")
    @ResponseBody
    public User getUser(@RequestParam int id) {
        return userMapper.GetUserById(id);
    }

    @RequestMapping("/users")
    @ResponseBody
    public List<User> getUsers() {
        return userMapper.GetUsersByPageNumber();
    }

    @RequestMapping("/summarys")
    @ResponseBody
    public List<ArticleSummary> getSummarys() {
        return summaryMapper.getSummarys();
    }

}