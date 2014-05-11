package com.malyshkin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "test";

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {

        return "test";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model, HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping(value = "/403")
    public String accessDenied(){
        return "403";
    }

}
