package com.bluemsun.news.service.serviceImpl;

import com.bluemsun.news.dao.NewsDao;
import com.bluemsun.news.entity.News;
import com.bluemsun.news.entity.NewsType;
import com.bluemsun.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mafx on 2019/5/15.
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
}
