package com.malyshkin.controller.user;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.CategoryType;
import com.malyshkin.entity.Transaction;
import com.malyshkin.entity.User;
import com.malyshkin.service.CategoryService;
import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CategoriesConroller {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "showCategoriesPage")
    public String showCategoriesPage(Model model){


        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        authentication.getName();

        User user = userService.findUser(authentication.getName());

        List<Category> categories = categoryService.findForCurrentMonth(user);

        List<Category> incomes = categories.stream().filter(c -> c.getType().equals(CategoryType.INCOME)).
                collect(Collectors.<Category>toList());
        List<Category> costs = categories.stream().filter(c -> c.getType().equals(CategoryType.COST)).
                collect(Collectors.<Category>toList());


        model.addAttribute("incomes", incomes);
        model.addAttribute("costs", costs);
        // Provide add category pop-up:
        model.addAttribute("category", new Category());
        // Provide add transaction pop-up:
        model.addAttribute("transaction", new Transaction());

        return "user/categories";
    }

    @RequestMapping(value = "addCategory")
    public String addCategory( Category category, BindingResult bindingResult){

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        authentication.getName();

        User user = userService.findUser(authentication.getName());

        category.setUser(user);
        category.setDate(new Date(Calendar.getInstance().getTimeInMillis()));

        categoryService.save(category);

        return "redirect:showCategoriesPage";
    }
}
