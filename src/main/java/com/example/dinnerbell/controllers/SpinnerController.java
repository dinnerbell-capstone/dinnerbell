package com.example.dinnerbell.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SpinnerController {

    @GetMapping("/spinner")
    public String spinnerpage() {
        return "spinner";
    }



//    Questionable code to discuss...
//    @GetMapping("/scratch")
//    public String showRoll() {
//        return "scratch";
//    }
//
//    @GetMapping("/scratch/{n}")
//    public String guess(@PathVariable int n, Model model) {
//        int randomNum = (int) (Math.random() * (7 - 1) + 1);
//        if (randomNum == 1) {
//            return "Taco Bell";
//        }
//        model.addAttribute("randomNum", "Congratulations! You will go eat at: " + randomNum + " tonight!");
//        model.addAttribute("randomRestaurant", "Your guess was: " + n);
//
//        return "/scratch";
//    }
}