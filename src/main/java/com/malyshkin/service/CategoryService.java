package com.malyshkin.service;

import com.malyshkin.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll();
    public void save(Category category);
}
