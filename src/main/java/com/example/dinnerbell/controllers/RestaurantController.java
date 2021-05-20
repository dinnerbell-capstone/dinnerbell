package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.repositories.CategoryRepo;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "business/create_account";
    }

    @PostMapping("/restaurant/create")
    public String createRestaurant(@ModelAttribute Restaurant restaurant){
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
        return "business/restaurant-profile";
    }

}