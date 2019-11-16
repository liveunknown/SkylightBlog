package com.skylight.blog.service.impl;

import com.skylight.blog.constant.RedisKeys;
import com.skylight.blog.mapper.FriendlinkMapper;
import com.skylight.blog.model.Friendlink;
import com.skylight.blog.service.FriendLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    FriendlinkMapper friendlinkMapper;

    public boolean addFriendLink(Friendlink friendlink){
        deleteFriendLinkListFromRedis();
        return friendlinkMapper.addFriendlink(friendlink);
    }

    public boolean deleteFriendLink(int id){
        deleteFriendLinkListFromRedis();
        return friendlinkMapper.deleteFriendlink(id);
    }

    public boolean updateFriendLink(Friendlink friendlink){
        deleteFriendLinkListFromRedis();
        return friendlinkMapper.updateFriendlink(friendlink);
    }

    public Friendlink getFriendLinkById(int id) {
        return friendlinkMapper.getFriendlinkById(id);
    }

    public List<Friendlink> getFriendLinkList(int isFamous){
        List<Friendlink> friendLinkList;
        String linkType = "";
        if(isFamous == 0) {
            linkType = RedisKeys.ORDINARYFRIENDLINKLIST;
        } else if (isFamous == 1) {
            linkType = RedisKeys.FAMOUSFRIENDLINKLIST;
        } else if (isFamous == 9) { // get all friend links
            linkType = RedisKeys.FRIENDLINKLIST;
        }

        if (redisTemplate.hasKey(linkType)) {
            friendLinkList = (List<Friendlink>)redisTemplate.opsForValue().get(linkType);
            logger.info("从 Redis 中获取友链了~");
        } else {
            friendLinkList = friendlinkMapper.getFriendlinkList(isFamous);
            //把数据库查询出来数据，放入Redis中
            redisTemplate.opsForValue().set(linkType,friendLinkList);
            logger.info("从 数据库 中获取友链了~");
        }
        return friendLinkList;
    }

    public void deleteFriendLinkListFromRedis(){
        if(redisTemplate.hasKey(RedisKeys.FRIENDLINKLIST)) {
            redisTemplate.delete(RedisKeys.FRIENDLINKLIST);
        }
        if(redisTemplate.hasKey(RedisKeys.FAMOUSFRIENDLINKLIST)) {
            redisTemplate.delete(RedisKeys.FAMOUSFRIENDLINKLIST);
        }
        if(redisTemplate.hasKey(RedisKeys.ORDINARYFRIENDLINKLIST)) {
            redisTemplate.delete(RedisKeys.ORDINARYFRIENDLINKLIST);
        }
    }
}
