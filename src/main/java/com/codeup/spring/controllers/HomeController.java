package com.codeup.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "This is the landing page!";
    }


    @GetMapping("/home")
    public String welcome() {
        return "/home";
    }

    @GetMapping("/join")
    public String showJoinForm(Model model) {
        List<String> cohortNames = new ArrayList<>();
        cohortNames.add("COBOL");
        cohortNames.add("Draco");

        model.addAttribute("cohortNames", cohortNames);
        return "/join";
    }

    @PostMapping("/join")
    public String postJoinForm(@RequestParam(name = "cohort") String cohort, Model model){

        model.addAttribute("cohort", "Welcome to " + cohort + " !");
        return "/join";
    }


}
