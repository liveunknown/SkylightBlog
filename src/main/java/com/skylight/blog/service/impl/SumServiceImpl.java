package com.skylight.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skylight.blog.constant.RedisKeys;
import com.skylight.blog.mapper.SumMapper;
import com.skylight.blog.service.SumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Service
public class SumServiceImpl implements SumService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SumMapper sumMapper;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    public int getArticleSum() {
        /*
        JSONObject jsonObject = getSiteInfo();
        return jsonObject.getInteger("articleNum");
        */
        int articleSum;
        if (redisTemplate.hasKey(RedisKeys.ARTICLESUM)) {
            articleSum = Integer.parseInt(redisTemplate.opsForValue().get(RedisKeys.ARTICLESUM));
            logger.info("从 Redis 中获取文章总数了~");
        } else {
            articleSum = sumMapper.getArticleSum();
            //把数据库查询出来数据，放入Redis中
            redisTemplate.opsForValue().set(RedisKeys.ARTICLESUM,String.valueOf(articleSum));
            logger.info("从 数据库 中获取文章总数了~");
        }
        return articleSum;
    }

    public int getArticleSumByCategoryId(Long categoryId) {
        if(categoryId==null)
            categoryId = new Long(0);
        return sumMapper.getArticleSumByCategoryId(categoryId);
    }

    public int getArticleSumByLabelId(Long labelId)
    {
        return sumMapper.getArticleSumByLabelId(labelId);
    }

    public JSONObject getSiteInfo() {
        JSONObject jsonObject = new JSONObject();

        if (redisTemplate.hasKey(RedisKeys.SITEINFO)) {
            jsonObject = JSON.parseObject(redisTemplate.opsForValue().get(RedisKeys.SITEINFO));
            logger.info("从 Redis 中获取网站信息了~");
        } else {
            jsonObject.put("articleNum", sumMapper.getArticleSum());
            jsonObject.put("categoryNum", sumMapper.getCategorySum());
            jsonObject.put("labelNum",sumMapper.getLabelSum());
            //把数据库查询出来数据，放入Redis中
            redisTemplate.opsForValue().set(RedisKeys.SITEINFO,JSON.toJSONString(jsonObject));
            logger.info("从 数据库 中获取网站信息了~");
        }
        return jsonObject;
    }
}
