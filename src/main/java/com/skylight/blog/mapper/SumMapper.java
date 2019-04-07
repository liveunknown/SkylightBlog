package com.skylight.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SumMapper {
    int getArticleSum();

    int getArticleSumByCategoryId(@Param("categoryId")Long categoryId);
}
