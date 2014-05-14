package com.malyshkin.service;


import com.malyshkin.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    public UserDetails findUserDetails(String email);
    public User findUser(String email);
    public void save(User user);

}

