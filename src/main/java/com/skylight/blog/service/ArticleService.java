package com.skylight.blog.service;

import com.skylight.blog.model.ArticleContent;
import com.skylight.blog.model.ArticleInfo;

import java.util.List;

public interface ArticleService {
    List<ArticleInfo> getArticleInfos(Long categoryId,int page, int number);

    ArticleContent getArticleContentByArticleInfoId(Long id);
}
