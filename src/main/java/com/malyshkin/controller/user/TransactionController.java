package com.malyshkin.controller.user;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.Transaction;
import com.malyshkin.service.CategoryService;
import com.malyshkin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.Calendar;

@Controller
public class TransactionController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TransactionService transactionService;


    @RequestMapping(value = "addTransaction")
    public String addTransaction(Transaction transaction){
        Category category = categoryService.findById(transaction.getCategory().getId());

        transaction.setCategory(category);
        transaction.setDate(new Date(Calendar.getInstance().getTimeInMillis()));

        transactionService.save(transaction);

        long sum = category.getSum();
        category.setSum(sum+transaction.getSum());

        categoryService.save(category);

        return "redirect:showCategoriesPage";
    }
}
