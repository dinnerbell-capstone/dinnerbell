package com.example.dinnerbell.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class SpinnerController {

    @GetMapping("/spinner")
    public String spinnerPage(Model model) {
        return "spinner";
    }
}
