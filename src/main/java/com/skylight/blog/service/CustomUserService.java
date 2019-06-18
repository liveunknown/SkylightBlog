package com.skylight.blog.service;

import com.skylight.blog.mapper.UserMapper;
import com.skylight.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;
    // 重写loadUserByUsername 方法获得 userdetails  类型用户
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.findByUserName(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}