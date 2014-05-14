package com.malyshkin.controller.user;

import com.malyshkin.entity.Category;
import com.malyshkin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoriesConroller {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "showCategoriesPage")
    public String showCategoriesPage(Model model){
        List<Category> incomes = categoryService.findAll();

        for(int i=0; i<3;i++) {
            Category category = new Category();
            category.setName("testCategoryName" + i);
            incomes.add(category);
        }

        model.addAttribute("incomes", incomes);
        model.addAttribute("testString", "testString");

        return "user/categories";
    }
}
