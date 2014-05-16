package com.malyshkin.controller.user;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StatisticController {

    @RequestMapping(value = "viewStatistic")
    public String viewStatistic(Model model){

        JsonObject incomes = new JsonObject();
        incomes.addProperty("name", "incomes");
        incomes.addProperty("sum", 60);


        JsonObject costs = new JsonObject();
        costs.addProperty("name", "costs");
        costs.addProperty("sum", 20);

        List<JsonObject> statistic = new ArrayList<>();
        statistic.add(incomes);
        statistic.add(costs);

        model.addAttribute("statistic", statistic);
//        model.addAttribute("statistic1", 1);

        return "user/statistic";
    }
}
