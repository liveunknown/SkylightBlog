package com.skylight.blog.interceptor;

import com.skylight.blog.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean flag = true;
        System.out.println("==============  request before  ==============");
        User user = (User) request.getSession().getAttribute("user");
        if(user==null)
        {
            request.getRequestDispatcher(request.getContextPath() + "/login.html").forward(request, response);
            flag = false;
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("==============  request  ==============");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("==============  request completion  ==============");
    }

}
