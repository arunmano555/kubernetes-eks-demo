package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public String hello() {
        return "Hello from Spring Boot API!";
    }
}
