package com.team12.fantafilm.service.impl;

import com.team12.fantafilm.model.Category;
import com.team12.fantafilm.repository.CategoryRepository;
import com.team12.fantafilm.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Boolean create(Category category) {
        try {
            categoryRepository.save(category);
            return  true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public Category fingById(Long id) {
        return null;
    }

    @Override
    public Boolean update(Category category) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
