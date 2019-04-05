package com.skylight.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SumMapper {
    int getArticleSum();
}
