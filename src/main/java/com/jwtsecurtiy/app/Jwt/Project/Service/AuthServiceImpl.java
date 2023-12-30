package com.jwtsecurtiy.app.Jwt.Project.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtsecurtiy.app.Jwt.Project.Entity.Customer;
import com.jwtsecurtiy.app.Jwt.Project.Repository.CustomerRepository;
import com.jwtsecurtiy.app.Jwt.Project.dto.SignUpRequest;

@Service
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createCustomer(SignUpRequest signUpRequest) {
        if(customerRepository.existsByEmail(signUpRequest.getEmail())){
             return false;
        }else{
             //model
            Customer customer = new Customer();
            BeanUtils.copyProperties(signUpRequest, customer);
            
            //hhash the password before saving
            String hashPassword = passwordEncoder.encode(signUpRequest.getPassword());
            customer.setPassword(hashPassword);
            customerRepository.save(customer);
            return true;
        }
    }
    
}
