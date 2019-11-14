package com.skylight.blog.controller;

import com.skylight.blog.constant.RedisKeys;
import com.skylight.blog.mapper.UpdateLogMapper;
import com.skylight.blog.model.UpdateLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UpdateLogController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UpdateLogMapper updateLogMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

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
        List<UpdateLog> updateLogList;
        if (redisTemplate.hasKey(RedisKeys.UPDATELOGLIST)) {
            updateLogList = (List<UpdateLog>)redisTemplate.opsForValue().get(RedisKeys.UPDATELOGLIST);
            logger.info("从 Redis 中获取更新日志了~");
        } else {
            updateLogList = updateLogMapper.getUpdateLogList();
            //把数据库查询出来数据，放入Redis中
            redisTemplate.opsForValue().set(RedisKeys.UPDATELOGLIST,updateLogList);
            logger.info("从 数据库 中获取更新日志了~");
        }

        return updateLogList;
    }

}
