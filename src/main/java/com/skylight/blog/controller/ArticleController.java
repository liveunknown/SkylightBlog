package com.skylight.blog.controller;

import com.skylight.blog.mapper.ArticleInfoMapper;
import com.skylight.blog.model.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleInfoMapper articleInfoMapper;

    @RequestMapping("/articleInfo")
    public List<ArticleInfo> getArticleInfo(Long categoryId,int page) {
        if(categoryId==null)
            categoryId = new Long(0);
        //return articleInfoMapper.getArticleInfos(categoryId,page,10);
        return articleInfoMapper.getArticleInfos(categoryId,0,10);
    }
}
