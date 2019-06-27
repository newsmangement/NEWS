package com.bluemsun.news.service.serviceImpl;

import com.bluemsun.news.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mafx
 * @date 2019/5/20
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> gr=new ArrayList<>();
        gr.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(username,userDao.getPwdByUsername(username),gr);
    }
}
