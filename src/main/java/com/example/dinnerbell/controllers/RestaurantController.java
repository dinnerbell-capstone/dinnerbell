package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.repositories.CategoryRepo;
import com.example.dinnerbell.repositories.FavoriteRestaurantRepo;
import com.example.dinnerbell.repositories.RestaurantCategoryRepo;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showCreateForm() {
        return "restaurant/create";
    }

    @PostMapping("/restaurant/create")
    public String create(
            @RequestParam(name = "restaurant_name") String restaurant_name,
            @RequestParam(name = "restaurant_description") String restaurant_description,
            @RequestParam(name = "restaurant_hours") String restaurant_hours,
            @RequestParam(name = "restaurant_street_address") String restaurant_street_address,
            @RequestParam(name = "restaurant_state") String restaurant_state
    ) {

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurant_name(restaurant_name);
        restaurant.setDescription(restaurant_description);
        restaurant.setHours(restaurant_hours);
        restaurant.setStreet_address(restaurant_street_address);
        restaurant.setState(restaurant_state);

        return "redirect: /restaurant/login";

    }
}