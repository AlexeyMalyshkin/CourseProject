package com.malyshkin.service;


import com.malyshkin.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails findUserDetails(String login, String password);

    UserDetails findUserDetails(String login);

    User findUser(String login, String password);

}

