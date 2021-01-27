//package com.codeup.spring.controllers;

//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class HomeController {
//
//    @GetMapping("/")
//    public String hello() {
//        return "landing";
//    }
//
//    @GetMapping("/join")
//    public String showJoinForm(Model model) {
//        List<String> cohortNames = new ArrayList<>();
//        cohortNames.add("COBOL");
//        cohortNames.add("Draco");
//
//        model.addAttribute("cohortNames", cohortNames);
//        return "/join";
//    }
//
//    @PostMapping("/join")
//    public String postJoinForm(@RequestParam(name = "cohort") String cohort, Model model){
//
//        model.addAttribute("cohort", "Welcome to " + cohort + " !");
//        return "/join";
//    }
//
//
//}
