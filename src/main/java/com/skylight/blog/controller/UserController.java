package com.skylight.blog.controller;

import com.skylight.blog.mapper.UserMapper;
import com.skylight.blog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/getUserByName")
    @ResponseBody
    public User getUserByName(String name) {
        return userMapper.findByUserName(name);
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public boolean deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public List<User> getUserList() {
        return userMapper.getUserList(1,10);
    }

    @RequestMapping("/modifyUser")
    @ResponseBody
    public boolean modifyUser(User user) {
        return userMapper.modifyUser(user);
    }

}
