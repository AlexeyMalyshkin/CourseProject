package com.malyshkin.controller;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.CategoryType;
import com.malyshkin.entity.Family;
import com.malyshkin.entity.FamilyInvite;
import com.malyshkin.entity.Role;
import com.malyshkin.entity.RoleType;
import com.malyshkin.entity.User;
import com.malyshkin.repository.specification.FamilyInviteRepository;
import com.malyshkin.service.CategoryService;
import com.malyshkin.service.FamilyInviteService;
import com.malyshkin.service.FamilyService;
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

    @Autowired
    private FamilyService familyService;

    @Autowired
    private FamilyInviteService familyInviteService;

    public void fillDbWithTempData(){

        // Roles
        Role roleUser = new Role();
        roleUser.setName(RoleType.USER.name());
        roleService.save(roleUser);

        //Families
        Family family = new Family();
        familyService.save(family);

        Family family2 = new Family();
        familyService.save(family2);


        // Users
        User user = new User();
        user.setEmail("admin");
        user.setPassword(shaPasswordEncoder.encodePassword("admin",null));
        user.setRole(roleUser);
        user.setFamily(family);
        userService.save(user);

        User user2 = new User();
        user2.setEmail("wife");
        user2.setPassword(shaPasswordEncoder.encodePassword("wife",null));
        user2.setRole(roleUser);
        user2.setFamily(family);
        userService.save(user2);

        User user3 = new User();
        user3.setEmail("son");
        user3.setPassword(shaPasswordEncoder.encodePassword("son",null));
        user3.setRole(roleUser);
        user3.setFamily(family);
        user3.setFamilyAdmin(true);
        userService.save(user3);

        User asd = new User();
        asd.setEmail("asd");
        asd.setPassword(shaPasswordEncoder.encodePassword("asd", null));
        asd.setRole(roleUser);
//        asd.setFamily(family);
        userService.save(asd);

        //Family invites
        FamilyInvite familyInvite = new FamilyInvite();
        familyInvite.setTo(user);
        familyInvite.setFrom(user2);
        familyInvite.setFamily(family);
        familyInviteService.save(familyInvite);

        //Family invites
//        FamilyInvite familyInvite2 = new FamilyInvite();
//        familyInvite2.setUser(user);
//        familyInvite2.setFamily(family2);
//        familyInviteService.save(familyInvite2);


        // Categories

        //  Incomes
        for(int i=0; i<3;i++) {
            Category category = new Category();
            category.setName("Income " + i);
            category.setType(CategoryType.INCOME);
            category.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
            category.setUser(user);
            category.setSum(20);

            categoryService.save(category);
        }

        for(int i=0; i<2;i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);

            Date monthAgo =  new Date(cal.getTimeInMillis());

            Category category = new Category();
            category.setName("Income " + i);
            category.setType(CategoryType.INCOME);
            category.setDate(monthAgo);
            category.setUser(user);
            category.setSum(30);

            categoryService.save(category);
        }
        for(int i=0; i<2;i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -2);

            Date monthAgo =  new Date(cal.getTimeInMillis());

            Category category = new Category();
            category.setName("Income " + i);
            category.setType(CategoryType.INCOME);
            category.setDate(monthAgo);
            category.setUser(user);
            category.setSum(40);

            categoryService.save(category);
        }

        // Costs
        for(int i=0; i<5;i++) {
            Category category = new Category();
            category.setName("Cost " + i);
            category.setType(CategoryType.COST);
            category.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
            category.setUser(user);
            category.setSum(50);

            categoryService.save(category);
        }



        // for wife:

        //  Incomes
        for(int i=0; i<2;i++) {
            Category category = new Category();
            category.setName("Income " + i);
            category.setType(CategoryType.INCOME);
            category.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
            category.setUser(user2);
            category.setSum(20);

            categoryService.save(category);
        }

        for(int i=0; i<4;i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -5);

            Date monthAgo =  new Date(cal.getTimeInMillis());

            Category category = new Category();
            category.setName("Income " + i);
            category.setType(CategoryType.INCOME);
            category.setDate(monthAgo);
            category.setUser(user2);
            category.setSum(30);

            categoryService.save(category);
        }
        for(int i=0; i<2;i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -4);

            Date monthAgo =  new Date(cal.getTimeInMillis());

            Category category = new Category();
            category.setName("Income " + i);
            category.setType(CategoryType.INCOME);
            category.setDate(monthAgo);
            category.setUser(user2);
            category.setSum(40);

            categoryService.save(category);
        }

        // Costs
        for(int i=0; i<10;i++) {
            Category category = new Category();
            category.setName("Cost " + i);
            category.setType(CategoryType.COST);
            category.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
            category.setUser(user2);
            category.setSum(50);

            categoryService.save(category);
        }
    }
}
