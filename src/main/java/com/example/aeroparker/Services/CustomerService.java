package com.example.aeroparker.Services;

import com.example.aeroparker.Model.Customer;
import com.example.aeroparker.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(Customer customer) {
        if (customerRepository.findByEmailAddress(customer.getEmailAddress()).isPresent()) {
            throw new IllegalStateException("Email already registered!");
        }
        customerRepository.save(customer);
    }

    public boolean isEmailAlreadyRegistered(String email) {
        // Check the database or repository if the email exists
        return customerRepository.existsByEmailAddress(email);
    }
    
}



