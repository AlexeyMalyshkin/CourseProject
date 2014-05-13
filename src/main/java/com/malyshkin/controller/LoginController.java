package com.malyshkin.controller;

import com.malyshkin.entity.RoleType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {

        return "test";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
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

    @RequestMapping(value = "/select")
    public String select() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        List<String> rights = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        if(rights.contains(RoleType.ADMIN.name())){
            return "admin/adminTest";
        } else if(rights.contains(RoleType.USER.name())){
            return "user/userTest";
        }

        else return "/loginfailed";
    }


}
