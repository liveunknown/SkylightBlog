package com.skylight.blog.mapper;

import com.skylight.blog.model.ArticleSummary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SummaryMapper {
    List<ArticleSummary> getSummarys();
}
