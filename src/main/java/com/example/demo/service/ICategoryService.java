package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Boolean create(Category category);
    Category fingById(Long id);
    Boolean update(Category category);
    Boolean delete(Long id);
}
