package com.skylight.blog.service.impl;

import com.skylight.blog.mapper.ArticleInfoMapper;
import com.skylight.blog.model.ArticleInfo;
import com.skylight.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleInfoMapper articleInfoMapper;

    public List<ArticleInfo> getArticleInfos(Long categoryId, int page, int number)
    {
        List<ArticleInfo> articleInfoList = articleInfoMapper.getArticleInfos(categoryId, (page - 1)*number, number);
        return articleInfoList;
    }
}
