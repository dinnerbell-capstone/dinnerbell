package com.example.dinnerbell.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SpinnerController {

    @GetMapping("/spinner")
    public String spinnerPage() {
        return "app/spinner";
    }

    @GetMapping("/spinner-results")
    public String spinnerResult() {
        return "app/userSelect";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "About";
    }

    @Value("${filestack_key}")
    private String fileStackApiKey;


    @RequestMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String apikey(){
        System.out.println(fileStackApiKey);
        return "const YelpApiKey = `" + fileStackApiKey + "`";
    }

//    Questionable code to discuss... Not sure whether to use javascript or java to generate the result
//    @GetMapping("/scratch")
//    public String showRoll() {
//        return "scratch";
//    }
//
//    @GetMapping("/scratch/{n}")
//    public String guess(@PathVariable int n, Model model) {
//        int randomNum = (int) (Math.random() * (7 - 1) + 1);
//        model.addAttribute("randomNum", "Congratulations! You will go eat at: " + randomNum + " tonight!");
//        model.addAttribute("randomRestaurant", "Your guess was: " + n);
//
//        return "/scratch";
//    }


    //        if (randomNum == 1) {
//            return "Taco Bell";
//        }
}
