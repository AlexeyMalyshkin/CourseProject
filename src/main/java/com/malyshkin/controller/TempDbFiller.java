package com.malyshkin.controller;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.CategoryType;
import com.malyshkin.entity.Role;
import com.malyshkin.entity.RoleType;
import com.malyshkin.entity.User;
import com.malyshkin.service.CategoryService;
import com.malyshkin.service.RoleService;
import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;

@Component
public class TempDbFiller {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    public void fillDbWithTempData(){

        // Roles
        Role roleUser = new Role();
        roleUser.setName(RoleType.USER.name());
        roleService.save(roleUser);


        // Users
        User user = new User();
        user.setEmail("admin");
        user.setPassword(shaPasswordEncoder.encodePassword("admin",null));
        user.setRole(roleUser);
        userService.save(user);

        // Categories

        //  Incomes
        for(int i=0; i<3;i++) {
            Category category = new Category();
            category.setName("Income" + i);
            category.setType(CategoryType.INCOME);
            category.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
            category.setUser(user);
            categoryService.save(category);
        }

        for(int i=0; i<2;i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -2);

            Date monthAgo =  new Date(cal.getTimeInMillis());

            Category category = new Category();
            category.setName("Income" + i);
            category.setType(CategoryType.INCOME);
            category.setDate(monthAgo);
            category.setUser(user);
            categoryService.save(category);
        }

        // Costs
        for(int i=0; i<5;i++) {
            Category category = new Category();
            category.setName("Cost" + i);
            category.setType(CategoryType.COST);
            category.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
            category.setUser(user);
            categoryService.save(category);
        }
    }
}
