package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Category;
import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.models.User;
import com.example.dinnerbell.repositories.CategoryRepo;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
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
    Restaurant restaurant = restaurantsdao.getOne(id);
      model.addAttribute("restaurants", restaurant);

    return "business/details";
  }

  @PostMapping("/restaurant/details/{id}")
  public String favoriteForm(@PathVariable("id") long id){
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Restaurant restaurant = restaurantsdao.getOne(id);
      List<User> favorites = restaurant.getFavorites();
      if (favorites.isEmpty()){
        favorites.add(user);
        restaurant.setFavorites(favorites);
        restaurantsdao.save(restaurant);
      }
      else{
        restaurant.setFavorites(null);
        restaurantsdao.save(restaurant);
      }

      return "redirect:/restaurant/details/{id}";
  }

//  @PostMapping("/restaurant/details/{id}")
//  public String removeFromFavorites(@RequestParam("id") long restIdToRemove){
//    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//      Restaurant restaurant = restaurantsdao.getOne(restIdToRemove);
//      List<User> favorites = restaurant.getFavorites();
//      favorites.remove(user);
//      restaurantsdao.save(restaurant);
//      return "redirect:/restaurant/details/{id}";
//  }




}
