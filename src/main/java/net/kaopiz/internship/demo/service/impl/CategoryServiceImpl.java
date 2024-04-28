package net.kaopiz.internship.demo.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Category;
import net.kaopiz.internship.demo.entity.Category;
import net.kaopiz.internship.demo.repository.ICategoryRepository;
import net.kaopiz.internship.demo.repository.ICategoryRepository;
import net.kaopiz.internship.demo.service.ICategoryService;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

	private final ICategoryRepository categoryRepository;

	@Override
	public Page<Category> getAllCities(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public List<Category> getAllCities() {
		return categoryRepository.findAll();
	}

	@Override
	public void addCategory(Category newCategory) {
		categoryRepository.save(newCategory);
	}

	@Override
	public void updateCategory(Category updatedCategory) {
		Category existingCategory = categoryRepository.findById(updatedCategory.getId()).orElse(null);
		if (existingCategory != null) {
			updatedCategory.setCreatedAt(existingCategory.getCreatedAt());
			updatedCategory.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			categoryRepository.save(updatedCategory);
		}
	}

	@Override
	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId).orElse(null);
	}

	@Override
	public void setCategoryActiveFlag(Long categoryId, boolean activeFlag) {
		Category category = getCategoryById(categoryId);
		if (category != null) {
			category.setActiveFlag(activeFlag);
			categoryRepository.save(category);
		}
	}

	@Override
	public Page<Category> searchCategories(UserSearchDTO userSearchDTO, Pageable pageable) {

		String keyword = userSearchDTO.getKeyword();
		String status = userSearchDTO.getStatus();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return categoryRepository.findByActiveFlagAndNameContainingOrderByCreatedAtDesc(true, keyword,
						pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return categoryRepository.findByActiveFlagAndNameContainingOrderByCreatedAtDesc(false, keyword,
						pageable);
			} else {
				return categoryRepository.findByNameContaining(keyword, pageable);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return categoryRepository.findByActiveFlagOrderByCreatedAtDesc(true, pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return categoryRepository.findByActiveFlagOrderByCreatedAtDesc(false, pageable);
			} else {
				return categoryRepository.findByNameContaining(keyword, pageable);
			}
		} else {
			return getAllCities(pageable);
		}
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		return categoryRepository.findByName(categoryName).orElse(null);
	}

}
