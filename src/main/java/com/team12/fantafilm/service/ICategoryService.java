package com.team12.fantafilm.service;

import com.team12.fantafilm.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Boolean create(Category category);
    Category fingById(Long id);
    Boolean update(Category category);
    Boolean delete(Long id);
}
