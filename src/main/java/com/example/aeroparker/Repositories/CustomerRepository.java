package com.example.aeroparker.Repositories;


import com.example.aeroparker.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmailAddress(String emailAddress);
    
    Optional<Customer> findByEmailAddress(String emailAddress);
}
