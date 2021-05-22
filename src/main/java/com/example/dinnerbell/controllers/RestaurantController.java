package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Category;
import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.repositories.CategoryRepo;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RestaurantController {

    private final RestaurantRepo restaurantsdao;
    private final CategoryRepo categoriesdao;
    private final UserRepo usersdao;


    public RestaurantController(RestaurantRepo restaurantsdao, CategoryRepo categoriesdao, UserRepo usersdao) {
        this.restaurantsdao = restaurantsdao;
        this.categoriesdao = categoriesdao;
        this.usersdao = usersdao;
    }


    @GetMapping("/restaurant/create")
    public String showCreateRestaurantForm(Model model) {
      model.addAttribute("restaurant", new Restaurant());
      model.addAttribute("categories", categoriesdao.findAll());
        return "business/create_account";
    }

    @PostMapping("/restaurant/create")
    public String createRestaurant(@ModelAttribute Restaurant restaurant,@RequestParam(name = "categories")List<Category> categories){
      restaurant.setCategories(categories);
      System.out.println();
        restaurantsdao.save(restaurant);
        return "redirect:/restaurant";
    }



//    @GetMapping("/restaurant/{id}")
//    public String showRestaurant(@PathVariable long id, Model model) {
//        model.addAttribute("restaurant", restaurantsdao.getOne(id));
//        return "retaurant/details";
//    }


    @GetMapping("/restaurant")
    public String restaurantProfile(Model model) {
      model.addAttribute("restaurants",restaurantsdao.findAll());
      return "business/restaurant-profile";
    }



  @GetMapping("/restaurant/details/{id}")
  public String restaurants(@PathVariable long id, Model model){
    model.addAttribute("restaurants", restaurantsdao.getOne(id));
    return "business/details";
  }

}
