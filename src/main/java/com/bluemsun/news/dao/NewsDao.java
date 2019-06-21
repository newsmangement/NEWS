package com.bluemsun.news.dao;

import com.bluemsun.news.entity.News;
import com.bluemsun.news.entity.NewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mafx on 2019/5/15.
 */
@Repository
public class NewsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addNews(News news){
        String sql="insert into news(title,type,text,authorId,time)value(?,?,?,?,?)";
        Object[] param={news.getTitle(),news.getType(),news.getText(),news.getAuthorId(),news.getTime()};
        return jdbcTemplate.update(sql,param);

    }

    public List<News> getAllNewsDao() {
        String sql="select * from news";
        RowMapper<News> rowMapper=new BeanPropertyRowMapper<News>(News.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    public int deleteNewsDao(int newsId) {
        String sql="delete from news where id=?";
        return jdbcTemplate.update(sql,newsId);
    }

    public List<NewsType> getNewsTypeDao() {
        String sql="select * from param_news";
        RowMapper<NewsType> rowMapper=new BeanPropertyRowMapper<NewsType>(NewsType.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    public News getNewsByIdDao(int newsId) {
        Object[] param={newsId};
        String sql="select * from news where id=?";
        RowMapper<News> rowMapper=new BeanPropertyRowMapper<News>(News.class);
        return jdbcTemplate.query(sql,param,rowMapper).get(0);
    }

    public List<News> getNewsByTypeDao(int newsType) {
        Object[] param={newsType};
        String sql="select * from news where type=?";
        RowMapper<News> rowMapper=new BeanPropertyRowMapper<News>(News.class);
        return jdbcTemplate.query(sql,param,rowMapper);
    }

    public List<News> sortNewsByAccessNumDao(int sortType) {
        String sql="";
        if(sortType==1){
            sql="select * from news order by viewsNum asc";
        }else {
            sql="select * from news order by viewsNum desc";
        }
        RowMapper<News> rowMapper=new BeanPropertyRowMapper<News>(News.class);
        return jdbcTemplate.query(sql,rowMapper);
    }
}
