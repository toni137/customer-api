package com.example.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerAPI {

    @GetMapping
    public Customer getCustomer(){
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John");
        customer.setEmail("test@test.com");
        customer.setPassword("password");
        
        return customer;
    }
    
}
