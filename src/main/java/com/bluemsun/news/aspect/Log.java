package com.bluemsun.news.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by mafx on 2019/5/23.
 */
@Aspect
@Component
public class Log {

    @Pointcut("execution(* com.bluemsun.news.controller.NewsController.deleteNews())")
    public void pointcutController(){

    }
    @Pointcut("@annotation(com.bluemsun.news.aspect.Anno)")
    public void controllerAspect(){

    }
    @Pointcut("within(com.bluemsun.news.service.serviceImpl.NewsServiceImpl)")
    public void pointcutWithin(){};

    @Before("pointcutWithin()")
    public void deleteLog(){
        System.out.println("删除新闻...");
    }
}
