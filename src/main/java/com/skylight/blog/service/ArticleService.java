package com.skylight.blog.service;

import com.skylight.blog.model.*;

import java.util.List;

public interface ArticleService {
    List<ArticleInfo> getArticleInfos(Long categoryId,int page, int number);

    ArticleContent getArticleContentByArticleInfoId(Long id);

    List<ArticleWrap> getArticleInfoDetails(Long categoryId, int page, int number);

    List<ArticleWrap> getArticleWraps(Long categoryId, int page, int number);

    ArticleWrap getArticleWrapByArticleInfoId(Long id);


    List<Category> getCategoryList();

    List<Label> getLabelList();


    List<ArticleLabel> getArticleLabelListByLabelId(Long id,int page, int number);

    ArticleWrap getArticleInfoDetailByArticleInfoId(Long id);

    List<ArticleWrap> getArticleInfoDetailsByLabelId(Long id,int page, int number);
}
