package com.skylight.blog.service;

import com.skylight.blog.model.ArticleContent;
import com.skylight.blog.model.ArticleInfo;
import com.skylight.blog.model.Category;
import com.skylight.blog.model.Label;

public interface ManageService {

    // Category
    boolean addCategory(String name);

    boolean deleteCategory(Long id);

    Category getCategoryById(Long id);

    boolean updateCategory(Category category);

    // Label
    boolean addLabel(String name);

    boolean deleteLabel(Long id);

    Label getLabelById(Long id);

    boolean updateLabel(Label label);

    // Article
    boolean addArticle(ArticleInfo articleInfo, ArticleContent articleContent, Long[] ids);

    boolean deleteArticle(Long id);

    boolean updateArticle(ArticleInfo articleInfo, ArticleContent articleContent);

    // ArticleLabel
    boolean addLabelByArticleId(Long articleId,Long labelId);

    boolean deleteArticleLabel(Long id);
}
