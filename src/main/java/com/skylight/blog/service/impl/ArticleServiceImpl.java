package com.skylight.blog.service.impl;

import com.skylight.blog.mapper.ArticleContentMapper;
import com.skylight.blog.mapper.ArticleInfoMapper;
import com.skylight.blog.model.ArticleContent;
import com.skylight.blog.model.ArticleInfo;
import com.skylight.blog.model.ArticleWrap;
import com.skylight.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    ArticleContentMapper articleContentMapper;

    public List<ArticleInfo> getArticleInfos(Long categoryId, int page, int number)
    {
        List<ArticleInfo> articleInfoList = articleInfoMapper.getArticleInfos(categoryId, (page - 1)*number, number);
        return articleInfoList;
    }

    public List<ArticleWrap> getArticleInfoDetails(Long categoryId, int page, int number)
    {
        List<ArticleWrap> articleWrapList = articleInfoMapper.getArticleInfoDetails(categoryId, (page - 1)*number, number);
        return articleWrapList;
    }

    public ArticleContent getArticleContentByArticleInfoId(Long id)
    {
        return articleContentMapper.getArticleContentByArticleInfoId(id);
    }

    public List<ArticleWrap> getArticleWraps(Long categoryId, int page, int number){
        List<ArticleWrap> articleWrapList = articleInfoMapper.getArticleWraps(categoryId, (page - 1)*number, number);
        return articleWrapList;
    }

    public ArticleWrap getArticleWrapByArticleInfoId(Long id){
        return articleInfoMapper.getArticleWrapByArticleInfoId(id);
    }
}
