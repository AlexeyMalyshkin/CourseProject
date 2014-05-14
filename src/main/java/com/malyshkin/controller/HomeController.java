package com.malyshkin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final String AUTHENTICATION_ANONYMOUS_USER = "anonymousUser";

//    @Autowired
//    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {


//        Category category = new Category();
//        category.setName("testCategory");
//        category.setType(CategoryType.COST);

//        categoryService.save(category);
//
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if(AUTHENTICATION_ANONYMOUS_USER.equals(authentication.getName())) {
            return "login";
        } else {
            return "redirect: select";
        }
    }
}
