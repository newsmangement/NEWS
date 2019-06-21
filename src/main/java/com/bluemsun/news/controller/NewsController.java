package com.bluemsun.news.controller;

import com.bluemsun.news.entity.News;
import com.bluemsun.news.entity.NewsType;
import com.bluemsun.news.entity.Result;
import com.bluemsun.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mafx on 2019/5/15.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @RequestMapping("/addNews")
    @ResponseBody
    public Result addNews(News news){
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        news.setTime(format.format(date));
        Result result=new Result();
        int status=newsService.addNews(news);
        result.setStatus(status);
        if(status==1){
            result.setMessage("添加成功！");
        }else {
            result.setMessage("添加失败！");
        }

        return result;

    }

    @RequestMapping("/getAllNews")
    @ResponseBody
    public List<News> getAllNews(){
        List<News> list=newsService.getAllNewsService();
        return list;
    }

    @RequestMapping("/deleteNews/{newsId}")
    @ResponseBody
    public Result deleteNews(@PathVariable int newsId){
        int status=newsService.deleteNews(newsId);
        Result result=new Result();
        result.setStatus(status);
        if(status==1){
            result.setMessage("删除成功！");

        }else {
            result.setMessage("删除失败！");
        }
        return result;
    }
    @RequestMapping("/getNewsType")
    @ResponseBody
    public List<NewsType> getNewsType(){
        return newsService.getNewsType();

    }

    @RequestMapping("/getNewsById/{newsId}")
    @ResponseBody
    public News getNewsById(@PathVariable int newsId){
        return newsService.getNewsById(newsId);
    }

    @RequestMapping("/getNewsByType/{newsType}")
    @ResponseBody
    public List<News> getNewsByType(@PathVariable int newsType){
        return newsService.getNewsByType(newsType);
    }

    @RequestMapping("/sortNewsByAccessNum/{sortType}")
    @ResponseBody
    public List<News> sortNewsByAccessNum(@PathVariable int sortType){
        return newsService.sortNewsByAccessNum(sortType);
    }


}
