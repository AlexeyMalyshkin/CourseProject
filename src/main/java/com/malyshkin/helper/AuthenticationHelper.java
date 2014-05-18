package com.malyshkin.helper;

import com.malyshkin.entity.User;
import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHelper {

    @Autowired
    private UserService userService;

    public User getUserFromAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        authentication.getName();

        return userService.findUser(authentication.getName());
    }
}
