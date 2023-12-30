package com.jwtsecurtiy.app.Jwt.Project.Service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtsecurtiy.app.Jwt.Project.Entity.Customer;
import com.jwtsecurtiy.app.Jwt.Project.Repository.CustomerRepository;
import java.util.Collections;

@Service
public class CustomerServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired 
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //write to login of customer check into db
        Customer customer = customerRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email "+email));
        return new User(customer.getEmail(), customer.getPassword(), Collections.emptyList());
    }
    
}
