package com.shop.onlineshop.controller;

import com.shop.onlineshop.service.CategoryService;
import com.shop.onlineshop.service.ProductService;
import com.shop.onlineshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping({"index", "/"})
    public ModelAndView index(Model model) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("productList", productService.listProduct());
        return mv;
    }
}
