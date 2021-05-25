package com.example.dinnerbell.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class SearchController {


    @GetMapping("/search")
    public String spinnerPage() {
        return "yelpSearch";
    }

}
