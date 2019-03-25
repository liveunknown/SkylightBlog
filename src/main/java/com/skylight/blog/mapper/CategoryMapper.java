package com.skylight.blog.mapper;


import com.skylight.blog.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> getCategoryList();

    boolean addCategory(@Param("c")Category category);

    boolean deleteCategory(@Param("id")Long id);

    Category getCategoryById(@Param("id")Long id);

    boolean updateCategory(@Param("c")Category category);
}
