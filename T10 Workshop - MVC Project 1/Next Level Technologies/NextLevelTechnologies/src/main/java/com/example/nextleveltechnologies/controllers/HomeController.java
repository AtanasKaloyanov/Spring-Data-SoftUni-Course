package com.example.nextleveltechnologies.controllers;

import com.example.nextleveltechnologies.dtos.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    // Request URL: http://localhost:8080/ , Request method: GET
    @GetMapping("/")
    public String index(Model model) {
    //    model.addAttribute("username", "Pesho");
       return "index";
    }



}
