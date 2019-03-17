package com.skylight.blog.service;

import com.skylight.blog.model.ArticleContent;
import com.skylight.blog.model.ArticleInfo;
import com.skylight.blog.model.ArticleWrap;

import java.util.List;

public interface ArticleService {
    List<ArticleInfo> getArticleInfos(Long categoryId,int page, int number);

    ArticleContent getArticleContentByArticleInfoId(Long id);

    List<ArticleWrap> getArticleWraps(Long categoryId, int page, int number);
}
