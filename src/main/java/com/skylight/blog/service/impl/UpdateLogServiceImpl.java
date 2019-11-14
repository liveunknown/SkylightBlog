package com.skylight.blog.service.impl;

import com.skylight.blog.constant.RedisKeys;
import com.skylight.blog.mapper.UpdateLogMapper;
import com.skylight.blog.model.UpdateLog;
import com.skylight.blog.service.UpdateLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UpdateLogServiceImpl implements UpdateLogService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UpdateLogMapper updateLogMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public boolean addUpdateLog(UpdateLog updateLog){
        deleteUpdateLogListFromRedis();
        return updateLogMapper.addUpdateLog(updateLog);
    }

    public boolean deleteUpdateLog(int id){
        deleteUpdateLogListFromRedis();
        return updateLogMapper.deleteUpdateLog(id);
    }

    public boolean updateUpdateLog(UpdateLog updateLog){
        deleteUpdateLogListFromRedis();
        return updateLogMapper.updateUpdateLog(updateLog);
    }

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

    public void deleteUpdateLogListFromRedis(){
        if(redisTemplate.hasKey(RedisKeys.UPDATELOGLIST)) {
            redisTemplate.delete(RedisKeys.UPDATELOGLIST);
        }
    }
}
