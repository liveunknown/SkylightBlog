package com.skylight.blog.controller;

import com.skylight.blog.model.ArticleContent;
import com.skylight.blog.model.ArticleInfo;
import com.skylight.blog.model.ArticleWrap;
import com.skylight.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/articleInfo")
    public List<ArticleInfo> getArticleInfo(Long categoryId,int page) {
        if(categoryId==null)
            categoryId = new Long(0);
        return articleService.getArticleInfos(categoryId,page,10);
    }

    @RequestMapping("/articleInfoDetails")
    public List<ArticleWrap> getArticleInfoDetails(Long categoryId, int page) {
        if(categoryId==null)
            categoryId = new Long(0);
        return articleService.getArticleInfoDetails(categoryId,page,10);
    }

    @RequestMapping("/articleContent")
    public ArticleContent getArticleContentByArticleInfoId(Long articleInfoId){
        return articleService.getArticleContentByArticleInfoId(articleInfoId);
    }

    @RequestMapping("/articleWrap")
    public List<ArticleWrap> getArticleWrap(Long categoryId, int page) {
        if(categoryId==null)
            categoryId = new Long(0);
        return articleService.getArticleWraps(categoryId,page,10);
    }

    @RequestMapping("/articleWrapDetail")
    public ArticleWrap getArticleWrapByArticleInfoId(Long id) {
        return articleService.getArticleWrapByArticleInfoId(id);
    }
}
