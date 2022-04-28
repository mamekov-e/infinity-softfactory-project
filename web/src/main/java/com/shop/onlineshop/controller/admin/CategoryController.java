package com.shop.onlineshop.controller.admin;

import com.shop.onlineshop.model.entities.Category;
import com.shop.onlineshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("category-form")
    public ModelAndView listCategory() {
        ModelAndView mv = new ModelAndView("admin/category/category-form");
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @PostMapping("add-category")
    public ModelAndView addCategory(Category category) {
        ModelAndView mv = new ModelAndView("admin/category/category-form");
        categoryService.addCategory(category);
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @GetMapping("delete-category/{categoryId}")
    public ModelAndView deleteCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView mv = new ModelAndView("admin/category/category-form");
        categoryService.deleteCategory(Long.parseLong(categoryId));
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @GetMapping("update-category/{categoryId}")
    public ModelAndView updateCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView mv = new ModelAndView("admin/category/update-category");
        System.out.println(categoryService.getCategory(Long.parseLong(categoryId)).get());
        mv.addObject("Category", categoryService.getCategory(Long.parseLong(categoryId)).get());
        return mv;
    }
}
