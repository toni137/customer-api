package com.example.demo.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable("id") long id) {
        return repo.findById(id);
    }  
    
    @PostMapping 
    public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer){
        //Validate input
        if(newCustomer.getName() == null || newCustomer.getEmail() == null || newCustomer.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }
        newCustomer = repo.save(newCustomer);
        URI location = 
            ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newCustomer.getId())
            .toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable("id") long id) {
        //validate input
        if(customer.getId() != id || customer.getName() == null || customer.getEmail() == null || customer.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        repo.save(customer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id){
        try{
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e){ //if id is null catch exception
            return ResponseEntity.badRequest().build();
        }
       
    }
}
