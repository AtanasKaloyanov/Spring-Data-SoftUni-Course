package com.example.nextleveltechnologies.controllers;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/rest/test")
     public String test() {
        return "test";
    }
}
