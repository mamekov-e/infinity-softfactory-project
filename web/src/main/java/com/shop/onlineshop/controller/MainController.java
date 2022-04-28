package com.shop.onlineshop.controller;

import com.shop.onlineshop.model.GoogleUserInfo;
import com.shop.onlineshop.model.entities.Product;
import com.shop.onlineshop.model.entities.User;
import com.shop.onlineshop.service.CategoryService;
import com.shop.onlineshop.service.ProductService;
import com.shop.onlineshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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
    public ModelAndView index(@ModelAttribute("user") @Valid User userDto, Principal principal, @AuthenticationPrincipal OAuth2User oauth2User) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        User user;
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("productList", productService.listProduct());

        if(!email.equals("anonymousUser")){
            user = getPrincipalUser(principal, oauth2User);
            if(user==null){
                user = userService.findByEmail(email);
            }
            if(user.getRole().equals("ADMIN")){
                mv = new ModelAndView("admin/index");
            }
        }

        return mv;
    }

    @GetMapping("/search")
    public ModelAndView searchProductByName(String keyword){
        ModelAndView mv = new ModelAndView("index");
        if(keyword!=null){
            List<Product> products = productService.findByName(keyword);
            mv.addObject("categoryList", categoryService.listCategory());
            mv.addObject("productList",products);
        }

        return mv;
    }

    public User getPrincipalUser(Principal principal, OAuth2User oAuth2User) {

        User user;

        if (oAuth2User != null) {
            GoogleUserInfo googleUserInfo = new GoogleUserInfo(oAuth2User.getAttributes());

            user = userService.findByEmail(googleUserInfo.getEmail());
        } else {
            user = userService.findByEmail(principal.getName());
        }


        return user;
    }
}
