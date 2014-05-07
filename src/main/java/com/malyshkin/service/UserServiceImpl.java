package com.malyshkin.service;

import com.malyshkin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;

//import com.malyshkin.domain.User;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(String login) {
        System.out.println("1111111111");
        Iterable<com.malyshkin.domain.User> list = userRepository.findAll();
         return new User("admin","admin",true,true,true,true, Arrays.asList(new SimpleGrantedAuthority("USER_ADMIN")));
    }
}
