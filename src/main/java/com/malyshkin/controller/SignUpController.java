package com.malyshkin.controller;

import com.malyshkin.entity.Role;
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
import java.util.List;

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

        Role roleAdmin = new Role();
        roleAdmin.setName(RoleType.ADMIN.name());
        Role roleUser = new Role();
        roleUser.setName(RoleType.USER.name());


        roleService.save(roleAdmin);
        roleService.save(roleUser);

        Role role = roleService.findByName(RoleType.USER.name());

        List<User> list = role.getUsers();

        user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), null));
        user.setRole(role);
        userService.save(user);



        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                AuthorityUtils.createAuthorityList(RoleType.USER.name()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:select";
    }
}
