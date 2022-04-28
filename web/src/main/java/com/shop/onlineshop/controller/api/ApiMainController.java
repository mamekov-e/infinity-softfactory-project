package com.shop.onlineshop.controller.api;

import com.shop.onlineshop.service.CategoryService;
import com.shop.onlineshop.service.ProductService;
import com.shop.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiMainController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


}
