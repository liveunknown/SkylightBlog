package com.skylight.blog.mapper;

import com.skylight.blog.model.Friendlink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendlinkMapper {
    boolean addFriendlink(@Param("fl")Friendlink friendlink);

    boolean deleteFriendlink(@Param("id")int id);

    boolean updateFriendlink(@Param("fl")Friendlink friendlink);

    Friendlink getFriendlinkById(@Param("id")int id);

    List<Friendlink> getFriendlinkList(@Param("isFamous")int isFamous);
}
