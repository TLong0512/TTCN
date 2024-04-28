package net.kaopiz.internship.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Category;
import net.kaopiz.internship.demo.entity.Category;

public interface ICategoryService {

	Page<Category> getAllCities(Pageable pageable);

	List<Category> getAllCities();

	Category getCategoryById(Long categoryId);

	Category getCategoryByName(String categoryName);

	void addCategory(Category newCategory);

	void updateCategory(Category updatedCategory);

	void deleteCategory(Long categoryId);

	void setCategoryActiveFlag(Long categoryId, boolean activeFlag);

	Page<Category> searchCategories(UserSearchDTO userSearchDTO, Pageable pageable);

}
