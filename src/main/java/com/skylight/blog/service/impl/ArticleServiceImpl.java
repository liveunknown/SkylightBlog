package com.skylight.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.skylight.blog.constant.RedisKeys;
import com.skylight.blog.mapper.ArticleInfoMapper;
import com.skylight.blog.mapper.CategoryMapper;
import com.skylight.blog.mapper.LabelMapper;
import com.skylight.blog.model.*;
import com.skylight.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    LabelMapper labelMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public List<Category> getCategoryList()
    {
        List<Category> categoryList;
        if (redisTemplate.hasKey(RedisKeys.CATEGORYLIST)) {
            categoryList = (List<Category>)redisTemplate.opsForValue().get(RedisKeys.CATEGORYLIST);
            logger.info("从 Redis 中获取分类列表了~");
        } else {
            categoryList = categoryMapper.getCategoryList();
            //把数据库查询出来数据，放入Redis中
            redisTemplate.opsForValue().set(RedisKeys.CATEGORYLIST,categoryList);
            logger.info("从 数据库 中获取分类列表了~");
        }
        return categoryList;
    }

    public List<Label> getLabelList()
    {
        List<Label> labelList;
        if (redisTemplate.hasKey(RedisKeys.LABELLIST)) {
            labelList = (List<Label>)redisTemplate.opsForValue().get(RedisKeys.LABELLIST);
            logger.info("从 Redis 中获取标签列表了~");
        } else {
            labelList = labelMapper.getLabelList();
            //把数据库查询出来数据，放入Redis中
            redisTemplate.opsForValue().set(RedisKeys.LABELLIST,labelList);
            logger.info("从 数据库 中获取标签列表了~");
        }
        return labelList;
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

        ArticleWrap articleWrap ;
        // logger.info("判断缓存数据是否存在" + redisTemplate.hasKey(RedisKeys.ARTICLE + id));

        if (redisTemplate.hasKey(RedisKeys.ARTICLE + id)) {
            articleWrap = JSONObject.parseObject(redisTemplate.opsForValue().get(RedisKeys.ARTICLE + id).toString(), ArticleWrap.class);
            logger.info("从 Redis 中获取文章了~");
        } else {
            articleWrap = articleInfoMapper.getArticleWrapByArticleInfoId(id);
            //把数据库查询出来数据，放入Redis中
            redisTemplate.opsForValue().set(RedisKeys.ARTICLE + id,articleWrap);
            logger.info("从 数据库 中获取文章了~");
        }

        return articleWrap;
    }

    public List<ArticleWrap> getArticleInfosByPageNumber(int page,int number)
    {
        return articleInfoMapper.getArticleInfosByPageNumber((page - 1)*number, number);
    }
}
