package com.skylight.blog.config;

import com.skylight.blog.filter.KaptchaAuthenticationFilter;
import com.skylight.blog.service.CustomUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler authenticationFailHandler;

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        System.out.println("加密后的密码是: ");
        String password = passwordEncoder().encode("yulei");
        System.out.println("加密后的密码是: "+password);

        return authenticationProvider;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService());
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * 配置忽略的静态文件，不加的话，登录之前页面的css,js不能正常使用，得登录之后才能正常.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略URL
        web.ignoring().antMatchers("/*.html", "/**/*.css", "/**/*.js", "/images/**", "/img/**", "/bootsnav/images/brand/logo-black.jpeg");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new KaptchaAuthenticationFilter("/login", "/loginError"), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/register", "/doRegister","/index")
                .permitAll()
                .antMatchers("/articleInfoList", "/articleWrapDetail","/articleInfoDetailsByCategoryId","/articleInfoDetailsByLabelId","/labels","/categories")
                .permitAll()
                .antMatchers("/getFriendLinkList", "/getUpdateLogList","/getSiteInfo")
                .permitAll()
                .antMatchers("/articleSum", "/articleSumByCategoryId","/articleSumByLabelId")
                .permitAll()
                .antMatchers("/getKaptchaImage","/verifyCode","/loginError")
                .permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailHandler)
                //.defaultSuccessUrl("/welcome")
                //.failureUrl("/loginError")//登录失败 返回error
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll(); //注销行为任意访问

    }
}