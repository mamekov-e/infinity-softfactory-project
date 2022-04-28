package com.shop.onlineshop.controller.admin;

import com.shop.onlineshop.model.entities.Product;
import com.shop.onlineshop.service.CategoryService;
import com.shop.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/product-form")
    public ModelAndView listProduct() {
        ModelAndView mv = new ModelAndView("admin/product/product-form");
        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("productList", productService.listProduct());
        return mv;
    }

    @PostMapping("/add-product")
    public ModelAndView addProduct(Product product) {
        ModelAndView mv = new ModelAndView("admin/product/product-form");
        productService.addProduct(product);
        mv.addObject("productList", productService.listProduct());
        return mv;
    }

    @GetMapping("/delete-product/{productId}")
    public ModelAndView deleteProduct(@PathVariable("productId")String productId) {
        ModelAndView mv = new ModelAndView("admin/product/product-form");
        productService.deleteProduct(Long.parseLong(productId));
        mv.addObject("productList", productService.listProduct());
        return mv;
    }

    @GetMapping("update-product/{productId}")
    public ModelAndView updateProduct(@PathVariable("productId")String productId) {
        ModelAndView mv = new ModelAndView("admin/product/update-product");
        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("Product", productService.getProductById(Long.parseLong(productId)).get());
        return mv;
    }
}
