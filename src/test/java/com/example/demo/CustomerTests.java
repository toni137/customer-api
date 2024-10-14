package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.example.demo.domain.Customer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerTests {

    @Autowired TestRestTemplate template;

    
    @Test
    //@Disabled
    public void testGetList() {

        Customer[] customers = 
            template.getForObject("/customers", Customer[].class);

        assertNotNull(customers);
        assertNotNull(customers[0]);
        assertNotNull(customers[0].getId());
        assertTrue(customers.length>0);
    }
    
    @Test
    //@Disabled
    public void testGetById() {

        Customer customer = 
            template.getForObject("/customer/{id}", Customer.class, 1);

        assertNotNull(customer);
        assertNotNull(customer.getId());
    }
}
