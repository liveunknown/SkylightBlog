package com.skylight.blog.controller;

import com.skylight.blog.mapper.UserMapper;
import com.skylight.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    @ResponseBody
    public boolean login(int id, String password, HttpServletRequest request, HttpServletResponse response){

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
          request.getSession().setAttribute("user", user);
          return true;
      }

      return false;
    }

}
