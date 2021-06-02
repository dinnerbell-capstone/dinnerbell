package com.example.dinnerbell.controllers;

import com.example.dinnerbell.repositories.CategoryRepo;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RandomSelectorController {
    @GetMapping("/random-selector")
    public String randomSelector() {
        return "app/randomSelector";
    }
}
