package com.skylight.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public boolean login(int id,String password) {

      System.out.println("11111111"+id);
      System.out.println("22222222"+password);

      return true;
    }

}
