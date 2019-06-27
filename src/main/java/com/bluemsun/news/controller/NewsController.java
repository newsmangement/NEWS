package com.bluemsun.news.controller;

import com.bluemsun.news.entity.News;
import com.bluemsun.news.entity.NewsType;
import com.bluemsun.news.entity.Result;
import com.bluemsun.news.service.CommonService;
import com.bluemsun.news.service.NewsService;
import com.bluemsun.news.service.SearchEngineService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>新闻管理处理器</p>
 * @author gancl
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    Jedis jedis;

    @Autowired
    CommonService commonService;

    @Autowired
    SearchEngineService searchEngineService;
    /**
     * <p>添加新闻</p>
     * @param news
     * @return Result
     */
    @RequestMapping("/addNews")
    @ResponseBody
    public Result addNews(News news){
        commonService.delCache("newsList");
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        //设置新闻发布时间
        news.setTime(format.format(date));
        Result result=new Result();
        int status=newsService.addNews(news);
        int id=newsService.getMaxId();
        System.out.println(id);
        news.setId(id);
        searchEngineService.addNews(JSONObject.fromObject(news).toString(),news.getId());
        try {
            Thread.sleep(5);
            if(jedis.exists("newsList")) {
                commonService.delCache("newsList");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result.setStatus(status);
        if(status==1){
            result.setMessage("添加成功！");
        }else {
            result.setMessage("添加失败！");
        }

        return result;

    }

    /**
     * <p>获取所有新闻</p>
     * @return List
     */
    @RequestMapping("/getAllNews")
    @ResponseBody
    public void getAllNews(HttpServletRequest req, HttpServletResponse resp){
        HttpServletResponse response=(HttpServletResponse)resp;
        HttpServletRequest request=(HttpServletRequest)req;
        response.setCharacterEncoding("utf-8");
        if(jedis.exists("newsList")){
            try {
                response.getWriter().write(jedis.get("newsList"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        List<News> list=newsService.getAllNewsService();
        try {
            response.getWriter().write(JSONArray.fromObject(list).toString());
            jedis.set("newsList", JSONArray.fromObject(list).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>通过新闻id删除新闻</p>
     * @param newsId
     * @return Result
     */
    @RequestMapping("/deleteNews/{newsId}")
    @ResponseBody
    public Result deleteNews(@PathVariable int newsId){
        commonService.delCache("newsList");
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

    /**
     * <p>获取新闻类型</p>
     * @return List
     */
    @RequestMapping("/getNewsType")
    @ResponseBody
    public List<NewsType> getNewsType(){
        return newsService.getNewsType();

    }

    /**
     * <p>通过新闻id获取新闻</p>
     * @param newsId
     * @return News
     */
    @RequestMapping("/getNewsById/{newsId}/{keyword}")
    @ResponseBody
    public News getNewsById(@PathVariable int newsId,@PathVariable String keyword){
        News news=newsService.getNewsById(newsId);
        String newKeyWord="<font color=\"red\">"+keyword+"</font>";
        news.setText(news.getText().replaceAll(keyword,newKeyWord));
        return news;
    }

    @RequestMapping("/getNewsById/{newsId}")
    @ResponseBody
    public News getNewsById(@PathVariable int newsId){
        News news=newsService.getNewsById(newsId);
        return news;
    }

    /**
     * <p>根据新闻类型进行新闻检索</p>
     * @param newsType
     * @return List
     */
    @RequestMapping("/getNewsByType/{newsType}")
    @ResponseBody
    public List<News> getNewsByType(@PathVariable int newsType){
        return newsService.getNewsByType(newsType);
    }

    /**
     * <p>依据访问量对新闻进行排序</p>
     * @param sortType
     * @return List
     */
    @RequestMapping("/sortNewsByAccessNum/{sortType}")
    @ResponseBody
    public List<News> sortNewsByAccessNum(@PathVariable int sortType){
        return newsService.sortNewsByAccessNum(sortType);
    }

    /**
     * <p>关键字搜索新闻</p>
     * @param keyword
     * @return List
     */
    @RequestMapping("/keywordSearch")
    @ResponseBody
    public void keywordSearch(@RequestParam("keyword") String keyword,HttpServletResponse response){

        //return newsService.keywordSearchService(keyword);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(searchEngineService.searchEng(keyword));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>修改新闻</p>
     * @param news
     * @return Result
     */
    @RequestMapping("/updateNews")
    @ResponseBody
    public Result updateNews(News news){
        commonService.delCache("newsList");
        return newsService.updateNewsService(news);
    }


}
