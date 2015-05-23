package com.malyshkin.controller.user;

import com.google.gson.JsonObject;
import com.malyshkin.entity.Category;
import com.malyshkin.entity.CategoryType;
import com.malyshkin.entity.User;
import com.malyshkin.helper.AuthenticationHelper;
import com.malyshkin.helper.DateHelper;
import com.malyshkin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StatisticController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @RequestMapping(value = "viewStatistic")
    public String viewStatistic(Model model) {
        return populateStatisticForMonth(DateHelper.getCurrentMonth(), model);
    }

    @RequestMapping(value = "viewStatisticForMonth")
    public String viewStatisticForMonth(Model model, @RequestParam(value = "month") int month) {
        return populateStatisticForMonth(month, model);
    }

    @RequestMapping(value = "viewFamilyForMonth")
    public String viewFamilyStatistic(Model model, @RequestParam(value = "month") int month) {
        return populateFamilyCommonStatistic(model, month);
    }

    private String populateStatisticForMonth(int month, Model model) {
        User user = authenticationHelper.getUserFromAuthentication();
        Map<Integer, List<Category>> categoryMap = categoryService.findForCurrentYear(user);

        Map<Integer, List<Category>> familyCategoryMap = new HashMap<>();

        if(categoryMap.isEmpty()){
            return "redirect:showCategoriesPage";
        }

        if(user.getFamily()!=null)
        {
            for (User member : user.getFamily().getUsers()){
                familyCategoryMap.putAll(categoryService.findForCurrentYear(member));
            }
        }

        model.addAttribute("hasFamily", user.getFamily().getUsers().size()!=1);

        model.addAttribute("monthNames", DateHelper.getMonthsNames(categoryMap.keySet()));
        model.addAttribute("familyMonthNames", DateHelper.getMonthsNames(familyCategoryMap.keySet()));

        model.addAttribute("statistic", populateJsonStatistic(categoryMap.get(month), false));
        model.addAttribute("commonStatistic", populateCommonStatistic(categoryMap.get(month)));
        model.addAttribute("months", categoryMap.keySet());
        model.addAttribute("familyMonths", familyCategoryMap.keySet());
        model.addAttribute("activeMonth", month);
        model.addAttribute("familyAdmin", user.isFamilyAdmin());

        return "user/statistic";
    }

    private List<JsonObject> populateJsonStatistic(List<Category> categories, boolean family) {
        List<JsonObject> statistic = new ArrayList<>();
        for (Category category : categories) {
            JsonObject categoryJson = new JsonObject();

            categoryJson.addProperty("name",family?category.getUser().getEmail()+" "+
              category.getName(): category.getName());
            categoryJson.addProperty("sum", category.getSum());

            statistic.add(categoryJson);
        }

        return statistic;
    }

    private List<JsonObject> populateCommonStatistic(List<Category> categories) {
        List<JsonObject> statistic = new ArrayList<>();

        int totalIncomes = 0;
        int totalCosts = 0;
        for (Category category : categories) {
            if (CategoryType.COST.equals(category.getType())) {
                totalCosts+=category.getSum();
            } else if(CategoryType.INCOME.equals(category.getType())){
                totalIncomes+=category.getSum();
            }
        }

        JsonObject incomesJson = new JsonObject();
        incomesJson.addProperty("name", "Incomes");
        incomesJson.addProperty("sum", totalIncomes);

        JsonObject costsJson = new JsonObject();
        costsJson.addProperty("name", "Costs");
        costsJson.addProperty("sum", totalCosts);

        statistic.add(incomesJson);
        statistic.add(costsJson);

        return statistic;
    }

    private String populateFamilyCommonStatistic(Model model, int month){
        User user = authenticationHelper.getUserFromAuthentication();
        Map<Integer, List<Category>> categoryMap = categoryService.findForCurrentYear(user);

        if(categoryMap.isEmpty()){
            return "redirect:showCategoriesPage";
        }

        Map<Integer, List<Category>> familyCategoryMap = new HashMap<>();

        if(user.getFamily()!=null)
        {
            // bycicle
            for (User member : user.getFamily().getUsers()){
                Map<Integer, List<Category>> newMap = categoryService.findForCurrentYear(member);
                for(int key : newMap.keySet()){
                    if(familyCategoryMap.containsKey(key)){
                        familyCategoryMap.get(key).addAll(newMap.get(key));
                    } else{
                        familyCategoryMap.put(key, newMap.get(key));
                    }
                }
            }
        }
        model.addAttribute("hasFamily", user.getFamily().getUsers().size()!=1);

        model.addAttribute("familyAdmin", user.isFamilyAdmin());

        model.addAttribute("familyMonthNames", DateHelper.getMonthsNames(familyCategoryMap.keySet()));

        model.addAttribute("monthNames", DateHelper.getMonthsNames(categoryMap.keySet()));
        model.addAttribute("statistic", populateJsonStatistic(familyCategoryMap.get(month), true));
        model.addAttribute("commonStatistic", populateCommonStatistic(familyCategoryMap.get(month)));
        model.addAttribute("familyMonths", familyCategoryMap.keySet());
        model.addAttribute("months", categoryMap.keySet());
        model.addAttribute("activeMonth", month);

        return "user/statistic";
    }
}
