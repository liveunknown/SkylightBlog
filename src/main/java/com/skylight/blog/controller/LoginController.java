package com.skylight.blog.controller;

import com.skylight.blog.mapper.UserMapper;
import com.skylight.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserMapper userMapper;


    @RequestMapping("/login")
    @ResponseBody
    public boolean login(int id,String password) {

      User user = userMapper.GetUserById(id);

      System.out.println("---------------: "+user);
      System.out.println("---------------: "+id);
      System.out.println("---------------: "+password);
      //System.out.println("---------------: "+(user.getPassword()==password));
      //System.out.println("---------------: "+(user.getPassword().equals(password)));

      if(user==null)
      {
          return false;
      }
      else if(user.getPassword().equals(password))
      {
          return true;
      }

      return false;
    }

}
