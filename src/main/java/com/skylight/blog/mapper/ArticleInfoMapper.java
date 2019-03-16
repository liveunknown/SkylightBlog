package com.skylight.blog.mapper;

import com.skylight.blog.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleInfoMapper {
    List<ArticleInfo> getArticleInfos(@Param("categoryId")Long categoryId, @Param("count")int count, @Param("number")int number);
}
