package com.bluemsun.news.dao;

import com.bluemsun.news.entity.News;
import com.bluemsun.news.entity.NewsType;
import com.bluemsun.news.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *<p>新闻系统持久层</p>
 * @author zhangxy
 */
@Repository
public class NewsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * <p>持久层新闻添加</p>
     * @param news
     * @return int
     */
    public int addNews(News news){
        String sql="insert into news(title,type,text,authorId,time)value(?,?,?,?,?)";
        Object[] param={news.getTitle(),news.getType(),news.getText(),news.getAuthorId(),news.getTime()};
        return jdbcTemplate.update(sql,param);

    }

    /**
     * <p>持久层获取所有新闻</p>
     * @return List
     */
    public List<News> getAllNewsDao() {
        String sql="select * from news";
        RowMapper<News> rowMapper=new BeanPropertyRowMapper<News>(News.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    /**
     * <p>持久层删除新闻</p>
     * @param newsId
     * @return int
     */
    public int deleteNewsDao(int newsId) {
        String sql="delete from news where id=?";
        return jdbcTemplate.update(sql,newsId);
    }

    /**
     * <p>持久层获取新闻类型</p>
     * @return List
     */
    public List<NewsType> getNewsTypeDao() {
        String sql="select * from param_news";
        RowMapper<NewsType> rowMapper=new BeanPropertyRowMapper<NewsType>(NewsType.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    /**
     * <p>通过新闻ID获取新闻具体内容</p>
     * @param newsId
     * @return News
     */
    public News getNewsByIdDao(int newsId) {
        Object[] param={newsId};
        String sql="select * from news where id=?";
        RowMapper<News> rowMapper=new BeanPropertyRowMapper<News>(News.class);
        return jdbcTemplate.query(sql,param,rowMapper).get(0);
    }

    /**
     * <p>通过新闻类型检索新闻</p>
     * @param newsType
     * @return List
     */
    public List<News> getNewsByTypeDao(int newsType) {
        Object[] param={newsType};
        String sql="select * from news where type=?";
        RowMapper<News> rowMapper=new BeanPropertyRowMapper<News>(News.class);
        return jdbcTemplate.query(sql,param,rowMapper);
    }

    /**
     * <p>通过访问量检索新闻</p>
     * @param sortType
     * @return List
     */
    public List<News> sortNewsByAccessNumDao(int sortType) {
        String sql="";
        //通过不同的排序类型，编写SQL
        if(sortType==1){
            sql="select * from news order by viewsNum asc";
        }else {
            sql="select * from news order by viewsNum desc";
        }
        RowMapper<News> rowMapper=new BeanPropertyRowMapper<News>(News.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    /**
     *<p>通过关键字检索新闻</p>
     * @param keyword
     * @return List
     */
    public List<News> keywordSearchDao(String keyword) {
        keyword="%"+keyword+"%";
        String sql="select * from news where title like ? or text like ?";
        Object[] param={keyword,keyword};
        RowMapper<News> rowMapper=new BeanPropertyRowMapper<News>(News.class);
        return jdbcTemplate.query(sql,rowMapper,param);
    }

    /**
     * <p>修改新闻</p>
     * @param news
     * @return int
     */
    public int updateNewsDao(News news) {
        String sql="update news set title=?,type=?,text=? where id=?";
        Object[] param={news.getTitle(),news.getType(),news.getText(),news.getId()};
        return jdbcTemplate.update(sql,param);
    }

    public int getMaxId() {
        String sql="SELECT max(id) from news";
        return jdbcTemplate.queryForObject(sql,Integer.class,null);
    }

    public void addUser(User user) {
        String sql="insert into users(name,username,password,phoneNum)value(?,?,?,?)";
        Object[] param={user.getName(),user.getUsername(),user.getPassword(),user.getPhoneNum()};
        jdbcTemplate.update(sql,param);
    }

    public Boolean isRignlePhoneNumDao(String phoneNum) {
        String sql="select count(*) from users where phoneNum=?";
        Object[] param={phoneNum};
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);

        return jdbcTemplate.queryForObject(sql,Integer.class,param)>0?false:true;
    }
}
