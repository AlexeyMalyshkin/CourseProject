package com.malyshkin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "login";
    }
//
//    @RequestMapping(value = "/user/hello")
//    public String hello1() {
//        return "user/hello";
//    }
//
//    @RequestMapping(value = "/admin**")
//    public String adminTest() {
//        return "admin/edit";
//    }
}
