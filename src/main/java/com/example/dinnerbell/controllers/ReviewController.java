package com.example.dinnerbell.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {


    @GetMapping("/review")
    public String reviewPage() {
        return "post/review";
    }

}
