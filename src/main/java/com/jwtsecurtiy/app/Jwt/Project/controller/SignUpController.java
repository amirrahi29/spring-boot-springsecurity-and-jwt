package com.jwtsecurtiy.app.Jwt.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jwtsecurtiy.app.Jwt.Project.Service.AuthService;
import com.jwtsecurtiy.app.Jwt.Project.dto.SignUpRequest;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    
    private final AuthService authService;

    @Autowired
    public SignUpController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> signUpCustomerEntity(@RequestBody SignUpRequest signUpRequest){
        boolean isUserCreated = authService.createCustomer(signUpRequest);
        if(isUserCreated){
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer Created Successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer creation failed");
        }
    }

}
