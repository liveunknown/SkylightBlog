package com.skylight.blog.controller;

import com.skylight.blog.mapper.FriendlinkMapper;
import com.skylight.blog.mapper.UserMapper;
import com.skylight.blog.model.Friendlink;
import com.skylight.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class FriendLinkController {

    @Autowired
    FriendlinkMapper friendlinkMapper;

    @RequestMapping("/addFriendLink")//@PostMapping("/addFriendLink")
    @ResponseBody
    public boolean addFriendLink(Friendlink friendlink){
       return friendlinkMapper.addFriendlink(friendlink);
    }

    @RequestMapping("/deleteFriendLink")
    @ResponseBody
    public boolean deleteFriendLink(int id){
        return friendlinkMapper.deleteFriendlink(id);
    }

    @RequestMapping("/updateFriendLink")
    @ResponseBody
    public boolean updateFriendLink(Friendlink friendlink){
        return friendlinkMapper.updateFriendlink(friendlink);
    }

    @RequestMapping("/getFriendLinkList")
    @ResponseBody
    public List<Friendlink> getFriendLinkList(int isFamous){
        return friendlinkMapper.getFriendlinkList(isFamous);
    }

}
