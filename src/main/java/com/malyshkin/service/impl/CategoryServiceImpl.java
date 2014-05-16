package com.malyshkin.service.impl;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.User;
import com.malyshkin.repository.CategoryRepository;
import com.malyshkin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<Category> findForCurrentMonth(User user) {
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);

        Date monthAgo =  new Date(cal.getTimeInMillis());
        return categoryRepository.findForCurrentMonth(user, monthAgo, currentDate);
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findOne(id);
    }
}

