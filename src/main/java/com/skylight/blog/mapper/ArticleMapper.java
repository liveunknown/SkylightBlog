package com.skylight.blog.mapper;

import com.skylight.blog.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
    Article getArticleById(@Param("id")int id);
}
