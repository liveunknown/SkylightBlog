package com.skylight.blog.mapper;

import com.skylight.blog.model.ArticleLabel;
import com.skylight.blog.model.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LabelMapper {
    List<Label> getLabelList();

    List<ArticleLabel> getArticleLabelListByLabelId(@Param("id")Long id, @Param("page")int page, @Param("number")int number);


    boolean addLabel(@Param("l")Label label);

    boolean deleteLabel(@Param("id")Long id);

    Label getLabelById(@Param("id")Long id);

    boolean updateLabel(@Param("l")Label label);

    // ArticleLabel
    boolean addArticleLabel(@Param("al")ArticleLabel articleLabel);

    boolean deleteArticleLabel(@Param("id")Long id);

    boolean deleteArticleLabelByArticleInfoId(@Param("id")Long id);
}
