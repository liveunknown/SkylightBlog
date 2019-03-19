package com.skylight.blog.service;

import com.skylight.blog.model.*;

import java.util.List;

public interface ArticleService {

    List<Category> getCategoryList();

    List<Label> getLabelList();

    List<ArticleWrap> getArticleInfoDetailsByLabelId(Long id,int page, int number);

    List<ArticleWrap> getArticleInfoDetails(Long categoryId, int page, int number);

    ArticleWrap getArticleWrapByArticleInfoId(Long id);
}
