package com.skylight.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.skylight.blog.service.SumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumController {

    @Autowired
    SumService sumService;

    @RequestMapping("/articleSum")
    public int getArticleSum() {
        return sumService.getArticleSum();
    }

    @RequestMapping("/articleSumByCategoryId")
    public int getArticleSumByCategoryId(Long categoryId) {
        return sumService.getArticleSumByCategoryId(categoryId);
    }

    @RequestMapping("/articleSumByLabelId")
    public int getArticleSumByLabelId(Long labelId)
    {
        return sumService.getArticleSumByLabelId(labelId);
    }

    @RequestMapping("/getSiteInfo")
    public JSONObject getSiteInfo() {
        return sumService.getSiteInfo();
    }
}
