package com.bluemsun.news.service;

import com.bluemsun.news.entity.News;
import com.bluemsun.news.entity.NewsType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mafx on 2019/5/15.
 */
@Service
public interface NewsService {
    int addNews(News news);

    List<News> getAllNewsService();

    int deleteNews(int newsId);

    List<NewsType> getNewsType();

    News getNewsById(int newsId);

    List<News> getNewsByType(int newsType);

    List<News> sortNewsByAccessNum(int sortType);
}
