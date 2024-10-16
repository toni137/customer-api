package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.Collections;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerTests {

    @Autowired TestRestTemplate template;

    @MockBean
    private CustomerRepository repo;

    @Test
    public void testPostByName() {
        Customer customer = new Customer();
        customer.setName("test");

        Mockito.when(repo.findAll()).thenReturn(Collections.singletonList(customer));

        ResponseEntity<Customer> response = template.postForEntity("/customers/byname", "test", Customer.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("test", response.getBody().getName());
    
    }
    
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

    @Test
    //@Disabled
    public void testGetByName() {

        Customer customer = 
            template.getForObject("/customer/byname/{username}", Customer.class, 1);

        assertNotNull(customer);
        assertNotNull(customer.getId());
    }

    @Test
    //@Disabled
    public void testPost() {

        Customer customer = new Customer(); 
        customer.setName("Test");
        customer.setEmail("test@test.com");
        customer.setPassword("password");

        URI location = template.postForLocation("/customers", customer, Customer.class);
        assertNotNull(location);

        customer = template.getForObject(location, Customer.class);
        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertEquals("Test", customer.getName());
        assertEquals("test@test.com", customer.getEmail());
        assertEquals("password", customer.getPassword());
    }


    @Test
    //@Disabled
    public void testPut() {

        String path = "/customers/2";
        String newValue = "NewValue" + Math.random();

        Customer customer = template.getForObject(path, Customer.class );

        customer.setName(newValue);
        template.put(path, customer);

        customer = template.getForObject(path, Customer.class );

        assertEquals(newValue, customer.getName());
    }

    @Test
    //@Disabled
    public void testDelete(){
        String path = "/customers/2";
        ResponseEntity<?> response = template.exchange(path, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

}
