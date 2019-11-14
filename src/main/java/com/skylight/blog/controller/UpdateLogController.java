package com.skylight.blog.controller;

import com.skylight.blog.model.UpdateLog;
import com.skylight.blog.service.UpdateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UpdateLogController {

    @Autowired
    UpdateLogService updateLogService;

    @RequestMapping("/addUpdateLog")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean addUpdateLog(UpdateLog updateLog){
        return updateLogService.addUpdateLog(updateLog);
    }

    @RequestMapping("/deleteUpdateLog")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteUpdateLog(int id){
        return updateLogService.deleteUpdateLog(id);
    }

    @RequestMapping("/updateUpdateLog")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean updateUpdateLog(UpdateLog updateLog){
        return updateLogService.updateUpdateLog(updateLog);
    }

    @RequestMapping("/getUpdateLogList")
    @ResponseBody
    public List<UpdateLog> getUpdateLogList(){
        return updateLogService.getUpdateLogList();
    }

}
