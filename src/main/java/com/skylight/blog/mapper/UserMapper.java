package com.skylight.blog.mapper;


import com.skylight.blog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

     User GetUserById(@Param("id")int id);

}
