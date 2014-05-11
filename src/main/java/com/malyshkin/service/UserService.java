package com.malyshkin.service;


import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {



    UserDetails getUser(String login);

}

