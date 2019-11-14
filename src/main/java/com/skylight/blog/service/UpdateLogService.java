package com.skylight.blog.service;

import com.skylight.blog.model.UpdateLog;

import java.util.List;

public interface UpdateLogService {
    boolean addUpdateLog(UpdateLog updateLog);

    boolean deleteUpdateLog(int id);

    boolean updateUpdateLog(UpdateLog updateLog);

    List<UpdateLog> getUpdateLogList();
}
