package com.bluemsun.news.dao;

import com.bluemsun.news.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mafx
 */
@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getPwdByUsername(String username){
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
        String sql="select * from users where username=?";
        List<User> list=jdbcTemplate.query(sql,rowMapper,username);
        if (list!=null){
            return list.get(0).getPassword();
        }
        return null;
    }
}
