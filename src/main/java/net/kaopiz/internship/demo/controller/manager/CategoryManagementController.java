package net.kaopiz.internship.demo.controller.manager;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.controller.common.BaseController;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Category;
import net.kaopiz.internship.demo.service.ICategoryService;

@Controller
@AllArgsConstructor
@RequestMapping("/mn/7/cate-mn")
public class CategoryManagementController extends BaseController {

	private final ICategoryService categoryService;

	@GetMapping()
	public String getAllCities(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, @ModelAttribute("search") UserSearchDTO search,
			Model model) {

		Page<Category> categoryPage = categoryService.searchCategories(search, PageRequest.of(page - 1, size));

		model.addAttribute("categoryPage", categoryPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", categoryPage.getTotalPages());

		model.addAttribute("category", new Category());

		model.addAttribute("categoryEdit", new Category());

		return "admin/category_admin";

	}

	@GetMapping("/getOne/{categoryId}")
	@ResponseBody
	public Optional<Category> getOne(@PathVariable Long categoryId, Model model) {
		return Optional.ofNullable(categoryService.getCategoryById(categoryId));
	}

	@PostMapping("/add")
	public String addNewCategory(@ModelAttribute("category") @Valid Category category, Model model,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/category_admin";
		}

		Category existedCategoryName = categoryService.getCategoryByName(category.getName());
		if (existedCategoryName == null) {
			categoryService.addCategory(category);
		} else {
			model.addAttribute("error", "This category name has been taken, please try again");
		}

		return "redirect:/mn/7/cate-mn";

	}

	@PostMapping("/edit")
	public String updateCategory(@ModelAttribute("categoryEdit") @Valid Category categoryEdit, Model model) {

		if (categoryEdit.getId() != null) {
			categoryService.updateCategory(categoryEdit);

		}

		return "redirect:/mn/7/cate-mn";
	}

	@GetMapping("/delete/{categoryId}")
	public String deleteCategory(@PathVariable Long categoryId) {

		categoryService.deleteCategory(categoryId);

		return "redirect:/mn/7/cate-mn";

	}

	@GetMapping("/disable/{categoryId}")
	public String disableCategory(@PathVariable Long categoryId) {
		categoryService.setCategoryActiveFlag(categoryId, false);
		return "redirect:/mn/7/cate-mn";
	}

	@GetMapping("/enable/{categoryId}")
	public String enableCategory(@PathVariable Long categoryId) {
		categoryService.setCategoryActiveFlag(categoryId, true);
		return "redirect:/mn/7/cate-mn";
	}

}
