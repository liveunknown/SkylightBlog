package com.skylight.blog.mapper;

import com.skylight.blog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User findByUserName(@Param("username") String username);


    boolean addUser(@Param("u") User user);

    boolean deleteUser(@Param("id") int id);

    User getUserById(@Param("id") int id);

    List<User> getUserList(@Param("page") int page,@Param("number") int number);

    boolean modifyUser(@Param("u")User user);
}
