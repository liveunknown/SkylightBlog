package com.skylight.blog.service;

import com.alibaba.fastjson.JSONObject;

public interface SumService {

    int getArticleSum();

    int getArticleSumByCategoryId(Long categoryId);

    int getArticleSumByLabelId(Long labelId);

    JSONObject getSiteInfo();

}
