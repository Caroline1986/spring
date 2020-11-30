package com.codeup.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice(){
        return "/roll";
    }

    @GetMapping("/roll-dice/{number}")
    public String rollDice(@PathVariable int number, Model model) {
        String response;
        Random random = new Random();
        int actual;
        while (true){
            actual = random.nextInt(7);
            if (actual !=0)
                break;
        }
        if (actual == number) {
            response = "Lucky guess";
        }else{
            response = "Try again";
        }

        model.addAttribute("number", number);
        model.addAttribute("actual", actual);
        model.addAttribute("response", response);
        return ("/roll-outcome");
    }
}
