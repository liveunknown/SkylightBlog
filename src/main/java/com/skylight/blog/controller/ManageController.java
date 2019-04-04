package com.skylight.blog.controller;

import com.skylight.blog.model.ArticleContent;
import com.skylight.blog.model.ArticleInfo;
import com.skylight.blog.model.Category;
import com.skylight.blog.model.Label;
import com.skylight.blog.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;
import java.util.List;

@RestController
//@RequestMapping("/admin")
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
    public boolean addArticle(String title,String summary,Long categoryId,String content,String labels){
        System.out.println("进入这个方法了： "+title  );
        System.out.println("进入这个方法了： "+summary  );
        System.out.println("进入这个方法了： "+categoryId  );
        System.out.println("进入这个方法了： "+content  );
        System.out.println("进入这个方法了： "+labels  );

        JSONObject json = new JSONObject();
        List<Long> labelList = json.parseArray(labels,Long.class);
        Long[] ids = labelList.toArray(new Long[labelList.size()]);

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
    public boolean updateArticle(Long articleId, String title, String summary, Long categoryId,Long contentId, String content){
        ArticleInfo articleInfo = new ArticleInfo();
        ArticleContent articleContent = new ArticleContent();
        articleInfo.setId(articleId);
        articleInfo.setTitle(title);
        articleInfo.setSummary(summary);
        articleInfo.setCategoryId(categoryId);

        articleContent.setId(contentId);
        articleContent.setContent(content);
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
