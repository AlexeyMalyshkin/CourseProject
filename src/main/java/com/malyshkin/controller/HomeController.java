package com.malyshkin.controller;

import com.malyshkin.entity.RoleType;
import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "login";
    }

    @RequestMapping(value = "/user/hello")
    public String hello1() {
        return "user/hello";
    }

    @RequestMapping(value = "/admin**")
    public String adminTest() {
        return "admin/edit";
    }

    @RequestMapping(value = "/select")
    public String select(Model model, HttpSession session) {
        System.out.println("Select");

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String login = authentication.getName();

        // Replace with somethong like usage "contains()" method. >_<
        for (Iterator<? extends GrantedAuthority> iterator = authentication.getAuthorities().iterator(); iterator.hasNext(); ) {
            GrantedAuthority authority = iterator.next();
            if (RoleType.ADMIN.name().equals(authority.getAuthority())) {
                return "admin/adminTest";
            }

            if (RoleType.USER.name().equals(authority.getAuthority())) {
                return "user/userTest";
            }
        }

        return "user/hello";
    }


}
