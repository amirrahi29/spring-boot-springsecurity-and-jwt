package com.jwtsecurtiy.app.Jwt.Project.Service;

import com.jwtsecurtiy.app.Jwt.Project.dto.SignUpRequest;

public interface AuthService {

    boolean createCustomer(SignUpRequest signUpRequest);
    
}
