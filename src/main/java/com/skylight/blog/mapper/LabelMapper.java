package com.skylight.blog.mapper;

import com.skylight.blog.model.Label;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LabelMapper {
    List<Label> getLabelList();
}
