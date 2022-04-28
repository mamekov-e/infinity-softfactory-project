package com.shop.onlineshop.controller.api;

import com.shop.onlineshop.model.entities.User;
import com.shop.onlineshop.service.UserService;
import com.shop.onlineshop.urils.requests.LoginRequest;
import com.shop.onlineshop.urils.requests.RegisterRequest;
import com.shop.onlineshop.urils.responses.LoginResponse;
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
    public ResponseEntity<LoginResponse> login(@RequestParam String email, @RequestParam String password) {
        User findUser = userService.findByEmail(email);

        LoginResponse loginResponse = new LoginResponse();
        if(findUser!=null){

            if(passwordEncoder.matches(password, findUser.getPassword())){
                loginResponse.setUser(findUser);
                loginResponse.setError(false);
                return ResponseEntity.ok().body(loginResponse);
            } else {
                loginResponse.setMessage("Invalid password");
                loginResponse.setError(true);
                return ResponseEntity.badRequest().body(loginResponse);
            }

        } else {
            loginResponse.setMessage("User not found");
            loginResponse.setError(true);
            return ResponseEntity.badRequest().body(loginResponse);
        }
    }

    @GetMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        User newUser = userService.findByEmail(registerRequest.getEmail());
        if(newUser==null){
            User user = new User();
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());
            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());
            userService.save(user);
            return ResponseEntity.ok("User created");
        } else {
            return ResponseEntity.badRequest().body("User exists");
        }
    }


}
