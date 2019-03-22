package com.skylight.blog.controller;

import com.skylight.blog.mapper.ArticleInfoMapper;
import com.skylight.blog.model.*;
import com.skylight.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleInfoMapper articleInfoMapper;


    @RequestMapping("/categories")
    public List<Category> getCategoryList()
    {
        return articleService.getCategoryList();
    }

    @RequestMapping("/labels")
    public List<Label> getLabelList()
    {
        return articleService.getLabelList();
    }

    @RequestMapping("/articleInfoDetailsByLabelId")
    public List<ArticleWrap> getArticleInfoDetailsByLabelId(Long id,int page) {
        return articleService.getArticleInfoDetailsByLabelId(id,page,10);
    }

    @RequestMapping("/articleInfoDetailsByCategoryId")
    public List<ArticleWrap> getArticleInfoDetailsByCategoryId(Long categoryId, int page) {
        if(categoryId==null)
            categoryId = new Long(0);
        return articleService.getArticleInfoDetails(categoryId,page,10);
    }

    @RequestMapping("/articleWrapDetail")
    public ArticleWrap getArticleWrapByArticleInfoId(Long id) {
        return articleService.getArticleWrapByArticleInfoId(id);
    }

    @RequestMapping("/addArticleInfo")
    public boolean addArticleInfo(String text) {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setIsDeleted(0);
        articleInfo.setCategoryId(new Long(1));
        articleInfo.setSummary(text);
        articleInfo.setViews(1);
        articleInfo.setTitle("md测试");
        return articleInfoMapper.addArticleInfo(articleInfo);
    }

    @RequestMapping("/aInfo")
    public ArticleInfo getArticleInfoById(Long id) {
        return articleInfoMapper.getArticleInfoById(id);
    }
}
