package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.repositories.*;
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
  private final ImageRepo imageDao;

  public ReviewController(RestaurantRepo restaurantsdao, CategoryRepo categoriesdao, UserRepo usersdao, ReviewRepo reviewDao, ImageRepo imageDao) {
    this.restaurantsdao = restaurantsdao;
    this.categoriesdao = categoriesdao;
    this.usersdao = usersdao;
    this.reviewDao = reviewDao;
    this.imageDao = imageDao;
  }

    @GetMapping("/review")
    public String reviewPage(Model model) {
        return "post/review";
    }

    @PostMapping("/review")
    public String reviewForm(Model model) {

      return "business/details";
    }

    private void

}
