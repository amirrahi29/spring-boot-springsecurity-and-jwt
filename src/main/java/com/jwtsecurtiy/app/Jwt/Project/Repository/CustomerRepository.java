package com.jwtsecurtiy.app.Jwt.Project.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jwtsecurtiy.app.Jwt.Project.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);
    
}
