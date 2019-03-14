package com.skylight.blog.config;

import com.skylight.blog.interceptor.VisitorInterceptor;
import com.skylight.blog.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.context.annotation.Bean;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/art").setViewName("article.html");
        registry.addViewController("/index").setViewName("login.html");
        super.addViewControllers(registry);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login");
        registry.addInterceptor(new VisitorInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html", "/admin/**","/index","/js/**");
        super.addInterceptors(registry);
        System.out.println("===========   拦截器注册完毕   ===========");
    }


}
