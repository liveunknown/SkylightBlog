package com.skylight.blog.mapper;

import com.skylight.blog.model.UpdateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UpdateLogMapper {
    boolean addUpdateLog(@Param("ul") UpdateLog updateLog);

    boolean deleteUpdateLog(@Param("id") int id);

    boolean updateUpdateLog(@Param("ul") UpdateLog updateLog);

    List<UpdateLog> getUpdateLogList();
}
