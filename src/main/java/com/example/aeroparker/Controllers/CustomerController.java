package com.example.aeroparker.Controllers;

import com.example.aeroparker.Model.Customer;
import com.example.aeroparker.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "registration";
    }

    @GetMapping("/success")
public String showSuccessPage() {
    return "success";  // Make sure you have a registration-success.html view.
}


@PostMapping("/registration")
public String processRegistrationForm(@Valid @ModelAttribute("customer") Customer customer, 
                                      BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
        // If there are validation errors, return to the form page
        return "registration";
    }

    try {
        // If the data is valid, save it to the database
        customerService.registerCustomer(customer);
    } catch (IllegalStateException e) {
        // If email is already registered, show an error message
        bindingResult.rejectValue("emailAddress", "error.emailAddress", e.getMessage());
        return "registration";
    }
        // Redirect to a success page
        return "success";
    }

    
}
