package com.bluemsun.news.service;

import com.bluemsun.news.entity.News;
import com.bluemsun.news.entity.NewsType;
import com.bluemsun.news.entity.Result;
import com.bluemsun.news.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author maj
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

    List<News> keywordSearchService(String keyword);

    Result updateNewsService(News news);

    int getMaxId();

    void addUser(User user);

    Boolean isRignlePhoneNum(String phoneNum);
}
