package com.bluemsun.news.controller;

import com.bluemsun.news.service.CommonService;
import com.bluemsun.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Created by mafx on 2019/5/20.</p>
 * <p>公共控制器，用于获取登录后的用户名</p>
 */
@RestController
@RequestMapping("/login")
public class CommonController {
    @Autowired
    Jedis jedis;

    @Autowired
    CommonService commonService;

    @Autowired
    NewsService newsService;
    /**
     * @return Map
     * <p>公共控制器，用于获取登录后的用户名</p>
     */
    @RequestMapping("/name")
    public Map getUsername(){
        //获取登录时的用户名
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        Map map=new HashMap<>();
        map.put("username",username);
        return  map;
    }

}
