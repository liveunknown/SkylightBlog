package com.skylight.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.skylight.blog.mapper.SumMapper;
import com.skylight.blog.service.SumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumController {

    @Autowired
    SumMapper sumMapper;

    @Autowired
    SumService sumService;

    @RequestMapping("/articleSum")
    public int getArticleSum() {
        return sumService.getArticleSum();
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
        return sumService.getSiteInfo();
    }
}
