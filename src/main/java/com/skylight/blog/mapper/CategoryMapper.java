package com.skylight.blog.mapper;


import com.skylight.blog.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> getCategoryList();
}
