package com.team12.fantafilm.controller.admin;

import com.team12.fantafilm.model.Category;
import com.team12.fantafilm.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping("/view-all/")
    public String viewCategory(Model model)
    {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories",categories);
        return "admin/category/index";
    }
    @GetMapping("/add-category")
    public String add(Model model)
    {
        Category category = new Category();
        model.addAttribute("category", category);
        return  "admin/category/add-category";
    }
}
