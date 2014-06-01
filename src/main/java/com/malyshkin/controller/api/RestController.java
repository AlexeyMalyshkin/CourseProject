package com.malyshkin.controller.api;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.Role;
import com.malyshkin.entity.RoleType;
import com.malyshkin.entity.User;
import com.malyshkin.service.CategoryService;
import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class RestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;
    //  http://localhost:8080/CourseProject/api/getFullInfo/admin

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @RequestMapping(value = "/getFullInfo/{email}/{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getFullInfoByEmail(@PathVariable String email, @PathVariable String password) {

        User user = userService.findUser(email);
        if(!user.getPassword().equals(shaPasswordEncoder.encodePassword(password,null))){
            return null;
        }

        user.setRole(new Role(){{setName(RoleType.USER.name());}});


        //ask
        Map<Integer,List<Category>> categoryList = categoryService.findForCurrentYear(user);


            user.setPassword(password);

        user.setRole(new Role());
        user.setCategories(new ArrayList<Category>(){{add(new Category()); add(new Category());}});

        return user;
    }


}
