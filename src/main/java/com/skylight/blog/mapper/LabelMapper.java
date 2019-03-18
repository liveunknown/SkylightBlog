package com.skylight.blog.mapper;

import com.skylight.blog.model.ArticleLabel;
import com.skylight.blog.model.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LabelMapper {
    List<Label> getLabelList();

    List<ArticleLabel> getArticleLabelListByLabelId(@Param("id")Long id);
}
