package com.skylight.blog.mapper;

import com.skylight.blog.model.ArticleWrap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleInfoMapper {
    List<ArticleWrap> getArticleInfoDetails(@Param("categoryId")Long categoryId, @Param("page")int page, @Param("number")int number);

    ArticleWrap getArticleWrapByArticleInfoId(@Param("id")Long id);

    ArticleWrap getArticleInfoDetailByArticleInfoId(@Param("id")Long id);
}
