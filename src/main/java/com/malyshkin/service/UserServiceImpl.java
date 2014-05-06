package com.malyshkin.service;

import com.malyshkin.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;

//import com.malyshkin.domain.User;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public User getUser(String login) {
        System.out.println("1111111111");
         return new User("admin","admin",true,true,true,true, Arrays.asList(new SimpleGrantedAuthority("USER_ADMIN")));
    }
}
