package com.skylight.blog.service.impl;

import com.skylight.blog.mapper.ArticleContentMapper;
import com.skylight.blog.mapper.ArticleInfoMapper;
import com.skylight.blog.mapper.CategoryMapper;
import com.skylight.blog.mapper.LabelMapper;
import com.skylight.blog.model.*;
import com.skylight.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    ArticleContentMapper articleContentMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    LabelMapper labelMapper;

    //不再使用
    public List<ArticleInfo> getArticleInfos(Long categoryId, int page, int number)
    {
        List<ArticleInfo> articleInfoList = articleInfoMapper.getArticleInfos(categoryId, (page - 1)*number, number);
        return articleInfoList;
    }
    public ArticleContent getArticleContentByArticleInfoId(Long id)
    {
        return articleContentMapper.getArticleContentByArticleInfoId(id);
    }
    public List<ArticleWrap> getArticleWraps(Long categoryId, int page, int number){
        List<ArticleWrap> articleWrapList = articleInfoMapper.getArticleWraps(categoryId, (page - 1)*number, number);
        return articleWrapList;
    }
    public List<ArticleLabel> getArticleLabelListByLabelId(Long id,int page, int number)
    {
        return labelMapper.getArticleLabelListByLabelId(id,(page - 1)*number, number);
    }
    public ArticleWrap getArticleInfoDetailByArticleInfoId(Long id)
    {
        return articleInfoMapper.getArticleInfoDetailByArticleInfoId(id);
    }
    //不再使用


    public List<Category> getCategoryList()
    {
        return categoryMapper.getCategoryList();
    }

    public List<Label> getLabelList()
    {
        return labelMapper.getLabelList();
    }

    public List<ArticleWrap> getArticleInfoDetailsByLabelId(Long id,int page, int number)
    {
        List<ArticleLabel> articleLabelList = labelMapper.getArticleLabelListByLabelId(id,(page - 1)*number, number);
        List<ArticleWrap> articleWrapList = new ArrayList<>(16);

        for(ArticleLabel articleLabel:articleLabelList)
        {
            ArticleWrap articleWrap = articleInfoMapper.getArticleInfoDetailByArticleInfoId(articleLabel.getArticleId());
            articleWrapList.add(articleWrap);
        }

        return articleWrapList;
    }

    public List<ArticleWrap> getArticleInfoDetails(Long categoryId, int page, int number)
    {
        List<ArticleWrap> articleWrapList = articleInfoMapper.getArticleInfoDetails(categoryId, (page - 1)*number, number);
        return articleWrapList;
    }

    public ArticleWrap getArticleWrapByArticleInfoId(Long id){
        return articleInfoMapper.getArticleWrapByArticleInfoId(id);
    }
}
