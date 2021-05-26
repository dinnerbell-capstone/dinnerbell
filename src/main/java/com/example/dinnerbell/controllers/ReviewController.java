package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.repositories.CategoryRepo;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.ReviewRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {
  private final RestaurantRepo restaurantsdao;
  private final CategoryRepo categoriesdao;
  private final UserRepo usersdao;
  private final ReviewRepo reviewDao;
  private final

    @GetMapping("/review")
    public String reviewPage(Model model) {
      Restaurant restaurant
      model.addAttribute("restaurant",)
        return "post/review";
    }

    @PostMapping("/review")
    public String reviewForm(Model model) {

      return "business/details";
    }

}
