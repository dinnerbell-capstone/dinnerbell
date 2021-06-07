package com.example.dinnerbell.controllers;

import com.example.dinnerbell.repositories.CategoryRepo;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


  @Value("${yelp_key}")
  private String yelpApiKey;

  @RequestMapping(path = "/keys.js", produces = "application/javascript")
  @ResponseBody
  public String apikey(){

    return "const yelpKey = `" + yelpApiKey + "`;";
  }

  @GetMapping("/yelp/events")
  public String indexExclusives(Model model) {
    // this will only grab created restaurants not yelp restaurants
//    model.addAttribute("restaurants", restaurantsdao.findAll());
    return "post/yelpExclusives";
  }

}
