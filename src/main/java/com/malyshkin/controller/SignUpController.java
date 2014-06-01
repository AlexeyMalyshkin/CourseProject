package com.malyshkin.controller;

import com.malyshkin.entity.RoleType;
import com.malyshkin.entity.User;
import com.malyshkin.service.RoleService;
import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    Validator userValidator;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ShaPasswordEncoder shaPasswordEncoder;

    @RequestMapping(value = "/signUpPage")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "signUp";
    }

    @RequestMapping(value = "signUp")
    public String signUp(@Valid User user, BindingResult bindingResult){

        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()){
            return "signUp";
        }


        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                AuthorityUtils.createAuthorityList(RoleType.USER.name()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:select";
    }
}
