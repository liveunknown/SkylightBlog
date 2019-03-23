package com.skylight.blog.mapper;

import com.skylight.blog.model.ArticleContent;
import com.skylight.blog.model.ArticleInfo;
import com.skylight.blog.model.ArticleWrap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleInfoMapper {
    List<ArticleWrap> getArticleInfoDetails(@Param("categoryId")Long categoryId, @Param("page")int page, @Param("number")int number);

    ArticleWrap getArticleWrapByArticleInfoId(@Param("id")Long id);

    ArticleWrap getArticleInfoDetailByArticleInfoId(@Param("id")Long id);

    // Only ArticleInfo
    boolean addArticleInfo(@Param("ai")ArticleInfo articleInfo);

    boolean deleteArticleInfo(@Param("id")Long id);

    ArticleInfo getArticleInfoById(@Param("id")Long id);

    boolean updateArticleInfo(@Param("ai")ArticleInfo articleInfo);

    // Only ArticleContent    No need for query
    boolean addArticleContent(@Param("ac")ArticleContent articleContent);

    boolean deleteArticleContent(@Param("id")Long id);

    boolean updateArticleContent(@Param("ac")ArticleContent articleContent);
}
