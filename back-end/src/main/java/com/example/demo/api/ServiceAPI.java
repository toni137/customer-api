package com.example.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ServiceAPI {
    
    @GetMapping
    public String ServerRunning(){
        return "The customer server is up and running";
    }
}
