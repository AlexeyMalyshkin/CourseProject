package com.malyshkin.service;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.User;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    public List<Category> findAll();
    public void save(Category category);
    public List<Category> findForCurrentMonth(User user);
    public Map<Integer, List<Category>> findForCurrentYear(User user);
    public Category findById(long id);
}
