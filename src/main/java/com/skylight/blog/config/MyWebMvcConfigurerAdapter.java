package com.skylight.blog.config;

import com.skylight.blog.interceptor.VisitorInterceptor;
import com.skylight.blog.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.context.annotation.Bean;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        super.addViewControllers(registry);
    }

    public void addInterceptors(InterceptorRegistry registry) {
/*      registry.addInterceptor(new WebInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login");
        registry.addInterceptor(new VisitorInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html", "/admin/**","/index","/js/**");*/
        registry.addInterceptor(new WebInterceptor()).addPathPatterns("/admins/**").excludePathPatterns("/admins/login");
        registry.addInterceptor(new VisitorInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html", "/admin/**","/index","/js/**");
        super.addInterceptors(registry);
        System.out.println("===========   拦截器注册完毕   ===========");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/image/**").addResourceLocations("file:C:/Users/Air/Desktop/images/");
        registry.addResourceHandler("/image/**").addResourceLocations("file:/usr/local/MyBlog/images/");
        super.addResourceHandlers(registry);
    }
}
