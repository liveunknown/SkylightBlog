package com.skylight.blog.controller;

import com.skylight.blog.model.Friendlink;
import com.skylight.blog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendLinkController {

    @Autowired
    FriendLinkService friendLinkService;

    @RequestMapping("/addFriendLink")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean addFriendLink(Friendlink friendlink){
       return friendLinkService.addFriendLink(friendlink);
    }

    @RequestMapping("/deleteFriendLink")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteFriendLink(int id){
        return friendLinkService.deleteFriendLink(id);
    }

    @RequestMapping("/updateFriendLink")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean updateFriendLink(Friendlink friendlink){
        return friendLinkService.updateFriendLink(friendlink);
    }

    @RequestMapping("/getFriendLinkList")
    @ResponseBody
    public List<Friendlink> getFriendLinkList(int isFamous){
        return friendLinkService.getFriendLinkList(isFamous);
    }

}
