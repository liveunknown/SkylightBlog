package com.skylight.blog.service;

import com.skylight.blog.model.*;

import java.util.List;

public interface ArticleService {
    // 不再使用
    List<ArticleInfo> getArticleInfos(Long categoryId,int page, int number);
    ArticleContent getArticleContentByArticleInfoId(Long id);
    List<ArticleWrap> getArticleWraps(Long categoryId, int page, int number);
    List<ArticleLabel> getArticleLabelListByLabelId(Long id,int page, int number);
    ArticleWrap getArticleInfoDetailByArticleInfoId(Long id);
    // 不再使用

    List<Category> getCategoryList();

    List<Label> getLabelList();

    List<ArticleWrap> getArticleInfoDetailsByLabelId(Long id,int page, int number);

    List<ArticleWrap> getArticleInfoDetails(Long categoryId, int page, int number);

    ArticleWrap getArticleWrapByArticleInfoId(Long id);
}
