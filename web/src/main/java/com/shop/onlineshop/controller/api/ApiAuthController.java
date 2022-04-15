package com.shop.onlineshop.controller.api;

import com.shop.onlineshop.model.entities.User;
import com.shop.onlineshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiAuthController {
    private static final Logger logger = LoggerFactory.getLogger(ApiAuthController.class);

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiAuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User findUser = userService.findByEmail(user.getEmail());
        if(findUser!=null){
            if(user.getPassword().equals(findUser.getPassword())){
                return ResponseEntity.ok().body("Successfully");
            } else return ResponseEntity.badRequest().body("Incorrect password");
        } else return ResponseEntity.badRequest().body("User not found");
    }

    @GetMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        User newUser = userService.findByEmail(user.getEmail());
        if(newUser==null){
            userService.save(user);
            return ResponseEntity.ok("User created");
        } else {
            return ResponseEntity.ok("User exists");
        }
    }
}
