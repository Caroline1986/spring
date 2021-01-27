package com.codeup.spring.controllers;

import com.codeup.spring.services.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaypalController {

    @Autowired
    PaypalService service;

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
