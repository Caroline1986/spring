package com.codeup.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @RequestMapping(path = "/add/{number}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int number, @PathVariable int number2) {
        return number + " plus " + (number2) + " is " + (number + number2) + " ! ";
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtract(@PathVariable int num1, @PathVariable int num2) {
        return String.format("This is the result of subtracting %d from %d:  %d", num1, num2, num1 - num2);
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiply(@PathVariable int num1, @PathVariable int num2) {
        return String.format("This is the result of multiplying %d and %d:  %d", num1, num2, num1 * num2);
    }

    @GetMapping("/divided/{num}/from/{num2}")
    @ResponseBody
    public String divide(@PathVariable int num, @PathVariable int num2) {
        return num + " divided by " + num2 + " is equal to " + (num/num2) + " ! ";
    }
}
