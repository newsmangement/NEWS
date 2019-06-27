package com.bluemsun.news.controller;

import com.bluemsun.news.entity.User;
import com.bluemsun.news.service.CommonService;
import com.bluemsun.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mafx on 2019/6/23.
 */
@Controller
@RequestMapping("/pub")
public class PubController {
    @Autowired
    Jedis jedis;

    @Autowired
    CommonService commonService;

    @Autowired
    NewsService newsService;

    @RequestMapping("/sendCode/{phoneNum}")
    @ResponseBody
    public Map sendCode(@PathVariable String phoneNum){
        String code=""+(int)((Math.random()*9+1)*10000);
        System.out.println(code);
        String message=phoneNum+","+code;
        jedis.set("code",code);
        jedis.expire("code",1000*60*5);
        commonService.sendMessageKafka(message);
        Map map=new HashMap<>();
        map.put("ms","信息发送成功");
        return map;


    }
    @RequestMapping(value = "/reginster/{code}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> reginter(HttpServletRequest request, @PathVariable String code){
        User user=new User();

        user.setName(request.getParameter("name"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setPhoneNum(request.getParameter("phoneNum"));
        Boolean flag=newsService.isRignlePhoneNum(request.getParameter("phoneNum"));
        HashMap<String,String> map=new HashMap<String,String>();
        if(!flag){
            map.put("ms","您输入的手机号已经注册过");
            return map;
        }
        String codeCache=jedis.get("code");

        if(codeCache!=null&&!(codeCache.equals(code))){
            map.put("ms","验证码错误");
            return map;
        }
        jedis.del("code");
        newsService.addUser(user);
        map.put("ms","注册成功");
        return map;


    }
}
