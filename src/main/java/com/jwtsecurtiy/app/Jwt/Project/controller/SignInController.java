package com.jwtsecurtiy.app.Jwt.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtsecurtiy.app.Jwt.Project.Service.jwt.CustomerServiceImpl;
import com.jwtsecurtiy.app.Jwt.Project.dto.BasicResponseMsg;
import com.jwtsecurtiy.app.Jwt.Project.dto.LoginRequest;
import com.jwtsecurtiy.app.Jwt.Project.dto.LoginResponse;
import com.jwtsecurtiy.app.Jwt.Project.utils.JwtUtil;

@RestController
@RequestMapping("/signin")
public class SignInController {
    
    private final AuthenticationManager authenticationManager;
    private CustomerServiceImpl customerServiceImpl;

    private final JwtUtil jwtUtil;

    @Autowired
    public SignInController(AuthenticationManager authenticationManager, CustomerServiceImpl customerServiceImpl,JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerServiceImpl = customerServiceImpl;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse responseMsg = new LoginResponse();
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails;

        try {
            userDetails = customerServiceImpl.loadUserByUsername(loginRequest.getEmail());
        } catch (UsernameNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //generate token
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        responseMsg.setStatus(200);
        responseMsg.setMessage("done");
        responseMsg.setToken(jwt);
        return ResponseEntity.ok(responseMsg);

    }

}
