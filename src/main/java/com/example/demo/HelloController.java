package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

@RestController
@RequestMapping("/hello") 
public class HelloController {

    @GetMapping("/get")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping
    public String postHello() {
        return "Created item";
    }


    @PutMapping("/{id}")
    public String putHello() {
        return "Updated item";
    }

    @DeleteMapping("/{id}")
    public String deleteHello(@PathVariable String id) {
        return "Deleted item with id: " + id;
    }

}

