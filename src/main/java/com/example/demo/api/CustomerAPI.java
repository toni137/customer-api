package com.example.demo.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerAPI {

   @Autowired CustomerRepository repo;

    @GetMapping
    public Iterable<Customer> getAllCustomers(){
        return repo.findAll();
    }

   /* @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") long id) {
        for(int i = 0; i < customerList.size(); i++) {
            if(customerList.get(i).getId() == id) {
                return customerList.get(i);
            }
        }
        return null;
    }
 */    
}
