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

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class HomeController {

  private final RestaurantRepo restaurantsdao;
  private final CategoryRepo categoriesdao;
  private final UserRepo usersdao;

  @Value("${filestack_key}")
  private String fileStackApiKey;

  public HomeController(RestaurantRepo restaurantsdao, CategoryRepo categoriesdao, UserRepo usersdao) {
    this.restaurantsdao = restaurantsdao;
    this.categoriesdao = categoriesdao;
    this.usersdao = usersdao;
  }

  @GetMapping("/")
  public String homepage() {
    return "app/home";
  }



  @RequestMapping(path = "/keys.js", produces = "application/javascript")
  @ResponseBody
  public String apikey(){
    System.out.println(fileStackApiKey);
    return "const FileStackApiKey = `" + fileStackApiKey + "`";
  }

  @GetMapping("/yelp/events")
  public String indexExclusives(Model model) {
    // this will only grab created restaurants not yelp restaurants
//    model.addAttribute("restaurants", restaurantsdao.findAll());
    return "post/yelpExclusives";
  }

  @GetMapping("/yelpRandomizer")
  public String spinnerPage() {
    return "business/randomSelector";
  }


  @GetMapping("/search")
  public String yelpSearch() {
    return "app/yelpSearch";
  }
}
