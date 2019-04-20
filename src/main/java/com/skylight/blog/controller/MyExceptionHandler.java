package com.skylight.blog.controller;


import com.skylight.blog.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map defaultExceptionHandler(Exception e) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", e.getMessage()+"来自Controller层的异常！");
        return map;
    }

/*    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Map myExceptionHandler(MyException e) {
        Map map = new HashMap();
        map.put("code", e.getCode());
        map.put("msg", e.getMsg());
        return map;
    }*/

    @ExceptionHandler(value = MyException.class)
    public ModelAndView myErrorHandler(MyException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code", e.getCode());
        modelAndView.addObject("msg", e.getMsg());
        return modelAndView;
    }
}
