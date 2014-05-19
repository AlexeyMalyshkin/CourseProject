package com.malyshkin.service.impl;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.User;
import com.malyshkin.repository.CategoryRepository;
import com.malyshkin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public void remove(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> findForCurrentMonth(User user) {
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);

        Date monthAgo = new Date(cal.getTimeInMillis());
        return categoryRepository.findForCurrentMonth(user, monthAgo, currentDate);
    }

    @Override
    public Map<Integer, List<Category>> findForCurrentYear(User user) {

        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);

        Date yearAgo = new Date(cal.getTimeInMillis());


        List<Category> yearList = categoryRepository.findForCurrentMonth(user, yearAgo, currentDate);

        Map<Integer, List<Category>> result = new HashMap<>();

        Set<Integer> monthes = new HashSet<>();

        for (Category category : yearList) {
            Calendar categoryDate = Calendar.getInstance();
            categoryDate.setTime(category.getDate());
            monthes.add(categoryDate.get(Calendar.MONTH));
        }

        for (Integer month : monthes) {
            List<Category> monthCategories = new ArrayList<>();
            for (Category category : yearList) {
                Calendar categoryDate = Calendar.getInstance();
                categoryDate.setTime(category.getDate());
                if (month.equals(categoryDate.get(Calendar.MONTH))) {
                    monthCategories.add(category);
                }
            }
            result.put(month, monthCategories);
        }

        return result;
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findOne(id);
    }
}

