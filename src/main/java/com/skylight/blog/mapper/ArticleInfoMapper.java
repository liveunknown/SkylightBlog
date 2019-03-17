package com.skylight.blog.mapper;

import com.skylight.blog.model.ArticleInfo;
import com.skylight.blog.model.ArticleWrap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleInfoMapper {
    List<ArticleInfo> getArticleInfos(@Param("categoryId")Long categoryId, @Param("page")int page, @Param("number")int number);
    List<ArticleWrap> getArticleWraps(@Param("categoryId")Long categoryId, @Param("page")int page, @Param("number")int number);
}
