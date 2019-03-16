package com.skylight.blog.mapper;

import com.skylight.blog.model.ArticleContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleContentMapper {
    ArticleContent getArticleContentByArticleInfoId(@Param("id")Long id);
}
