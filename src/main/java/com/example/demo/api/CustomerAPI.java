package com.example.demo.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerAPI {

    ArrayList<Customer> customerList = new ArrayList<Customer>();

    public CustomerAPI(){
        Customer customer1 = new Customer(1,"John","test@test.com","password");        
        customerList.add(customer1);

        Customer customer2 = new Customer(2,"Ann","email@test.com","admin");
        customerList.add(customer2);
    }

    @GetMapping
    public ArrayList<Customer> getAllCustomer(){
        return customerList;
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") long id) {
        for(int i = 0; i < customerList.size(); i++) {
            if(customerList.get(i).getId() == id) {
                return customerList.get(i);
            }
        }
        return null;
    }
    
}
