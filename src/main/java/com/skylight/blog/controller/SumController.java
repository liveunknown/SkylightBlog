package com.skylight.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.skylight.blog.mapper.SumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumController {

    @Autowired
    SumMapper sumMapper;

    @RequestMapping("/articleSum")
    public int getArticleSum() {
        return sumMapper.getArticleSum();
    }

    @RequestMapping("/articleSumByCategoryId")
    public int getArticleSumByCategoryId(Long categoryId) {
        if(categoryId==null)
            categoryId = new Long(0);
        return sumMapper.getArticleSumByCategoryId(categoryId);
    }

    @RequestMapping("/articleSumByLabelId")
    public int getArticleSumByLabelId(Long labelId)
    {
        return sumMapper.getArticleSumByLabelId(labelId);
    }

    @RequestMapping("/getSiteInfo")
    public JSONObject getSiteInfo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("articleNum", sumMapper.getArticleSum());
        jsonObject.put("categoryNum", sumMapper.getCategorySum());
        jsonObject.put("labelNum",sumMapper.getLabelSum());
        return jsonObject;
    }
}
