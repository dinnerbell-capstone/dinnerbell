package com.example.dinnerbell.controllers;

import com.example.dinnerbell.repositories.CategoryRepo;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private final RestaurantRepo restaurantsdao;
  private final CategoryRepo categoriesdao;
  private final UserRepo usersdao;

  public HomeController(RestaurantRepo restaurantsdao, CategoryRepo categoriesdao, UserRepo usersdao) {
    this.restaurantsdao = restaurantsdao;
    this.categoriesdao = categoriesdao;
    this.usersdao = usersdao;
  }

  @GetMapping("/")
  public String homepage() {
    return "app/home";
  }


  @GetMapping("/yelp/events")
  public String indexExclusives(Model model) {
    // this will only grab created restaurants not yelp restaurants
//    model.addAttribute("restaurants", restaurantsdao.findAll());
    return "post/yelpExclusives";
  }

}
