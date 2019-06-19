package com.skylight.blog.controller;

import com.skylight.blog.mapper.UpdateLogMapper;
import com.skylight.blog.model.UpdateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UpdateLogController {

    @Autowired
    UpdateLogMapper updateLogMapper;

    @RequestMapping("/addUpdateLog")//@PostMapping("/addFriendLink")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean addUpdateLog(UpdateLog updateLog){
       return updateLogMapper.addUpdateLog(updateLog);
    }

    @RequestMapping("/deleteUpdateLog")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteUpdateLog(int id){
        return updateLogMapper.deleteUpdateLog(id);
    }

    @RequestMapping("/updateUpdateLog")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean updateUpdateLog(UpdateLog updateLog){
        return updateLogMapper.updateUpdateLog(updateLog);
    }

    @RequestMapping("/getUpdateLogList")
    @ResponseBody
    public List<UpdateLog> getUpdateLogList(){
        return updateLogMapper.getUpdateLogList();
    }

}
