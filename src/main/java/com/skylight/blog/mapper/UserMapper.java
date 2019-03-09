package com.skylight.blog.mapper;


import com.skylight.blog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

     User GetUserById(@Param("id")int id);
     List<User> GetUsersByPageNumber();
}
