package com.security.spring_security_parte3y4.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.security.spring_security_parte3y4.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long>{
    Optional<Customer> findByEmail(String email);
}
