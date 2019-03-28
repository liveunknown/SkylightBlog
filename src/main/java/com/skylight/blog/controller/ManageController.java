package com.skylight.blog.controller;

import com.skylight.blog.model.ArticleContent;
import com.skylight.blog.model.ArticleInfo;
import com.skylight.blog.model.Category;
import com.skylight.blog.model.Label;
import com.skylight.blog.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ManageController {

    @Autowired
    ManageService manageService;

    // Category
    @RequestMapping("/addCategory")
    public boolean addCategory(String name) {
        return manageService.addCategory(name);
    }

    @RequestMapping("/deleteCategory")
    public boolean deleteCategory(Long id) {
        return manageService.deleteCategory(id);
    }

    @RequestMapping("/getCategory")
    @ResponseBody
    public Category getCategoryById(Long id){
        return manageService.getCategoryById(id);
    }
    @RequestMapping("/updateCategory")
    public boolean updateCategory(Category category){
        return manageService.updateCategory(category);
    }

    // Label
    @RequestMapping("/addLabel")
    public boolean addLabel(String name){
        return manageService.addLabel(name);
    }

    @RequestMapping("/deleteLabel")
    public boolean deleteLabel(Long id){
        return manageService.deleteLabel(id);
    }

    @RequestMapping("/getLabel")
    @ResponseBody
    public Label getLabelById(Long id){
        return manageService.getLabelById(id);
    }

    @RequestMapping("/updateLabel")
    public boolean updateLabel(Label label){
        return manageService.updateLabel(label);
    }

    // Article
    @RequestMapping("/addArticle")
    public boolean addArticle(String title,String summary,Long categoryId,String content, Long[] ids){
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle(title);
        articleInfo.setSummary(summary);
        articleInfo.setCategoryId(categoryId);
        articleInfo.setViews(0);
        articleInfo.setIsDeleted(0);

        ArticleContent articleContent = new ArticleContent();
        articleContent.setContent(content);

        return manageService.addArticle(articleInfo,articleContent,ids);
    }

    @RequestMapping("/deleteArticle")
    public boolean deleteArticle(Long id){
        return manageService.deleteArticle(id);
    }

    @RequestMapping("/updateArticle")
    public boolean updateArticle(ArticleInfo articleInfo, ArticleContent articleContent){
        return manageService.updateArticle(articleInfo, articleContent);
    }

    @RequestMapping("/addLabelByArticleId")
    public boolean addLabelByArticleId(Long articleId,Long labelId){
        return manageService.addLabelByArticleId(articleId, labelId);
    }

    @RequestMapping("/deleteArticleLabel")
    public boolean deleteArticleLabel(Long id){
        return manageService.deleteArticleLabel(id);
    }
}
