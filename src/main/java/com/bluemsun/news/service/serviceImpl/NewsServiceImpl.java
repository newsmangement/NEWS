package com.bluemsun.news.service.serviceImpl;

import com.bluemsun.news.dao.NewsDao;
import com.bluemsun.news.entity.News;
import com.bluemsun.news.entity.NewsType;
import com.bluemsun.news.entity.Result;
import com.bluemsun.news.entity.User;
import com.bluemsun.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author maj
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsDao newsDao;

    @Override
    public int addNews(News news) {

        return newsDao.addNews(news);
    }

    @Override
    public List<News> getAllNewsService() {
        return newsDao.getAllNewsDao();
    }

    @Override
    public int deleteNews(int newsId) {
        return newsDao.deleteNewsDao(newsId);
    }

    @Override
    public List<NewsType> getNewsType() {
        return newsDao.getNewsTypeDao();
    }

    @Override
    public News getNewsById(int newsId) {
        return newsDao.getNewsByIdDao(newsId);
    }

    @Override
    public List<News> getNewsByType(int newsType) {
        return newsDao.getNewsByTypeDao(newsType);
    }

    @Override
    public List<News> sortNewsByAccessNum(int sortType) {
        return newsDao.sortNewsByAccessNumDao(sortType);
    }

    @Override
    public List<News> keywordSearchService(String keyword) {

        return newsDao.keywordSearchDao(keyword);
    }

    @Override
    public Result updateNewsService(News news) {
        int flag=newsDao.updateNewsDao(news);
        Result result=new Result();
        if(flag==1){
            result.setMessage("修改成功");
            result.setStatus(flag);
        }else {
            result.setStatus(0);
            result.setMessage("修改失败");
        }
        return result;
    }

    @Override
    public int getMaxId() {
        return newsDao.getMaxId();
    }

    @Override
    public void addUser(User user) {
       newsDao.addUser(user);
    }

    @Override
    public Boolean isRignlePhoneNum(String phoneNum) {
        return newsDao.isRignlePhoneNumDao(phoneNum);
    }
}
