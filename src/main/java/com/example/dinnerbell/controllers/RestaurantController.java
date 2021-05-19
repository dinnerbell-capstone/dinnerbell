package com.example.dinnerbell.controllers;

import com.example.dinnerbell.repositories.FavoriteRestaurantRepo;
import com.example.dinnerbell.repositories.RestaurantCategoryRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {

    private final RestaurantCategoryRepo restaurantsdao;
    private final FavoriteRestaurantRepo favoritesdao;
    private final UserRepo usersdao;



    public RestaurantController(RestaurantCategoryRepo restaurantsdao, FavoriteRestaurantRepo favoritesDao, FavoriteRestaurantRepo favoritesdao, UserRepo usersdao) {
        this.restaurantsdao = restaurantsdao;
        this.favoritesdao = favoritesdao;
        this.usersdao = usersdao;
    }

    @Controller
    public class AdController {
        @GetMapping("/ads/create")
        public String showCreateForm() {
            return "ads/create";
        }

        @PostMapping("/ads/create")
        public String create(
                @RequestParam(name = "title") String title,
                @RequestParam(name = "description") String description
        ) {
            Ad ad = new Ad();
            ad.setTitle(title);
            ad.setDescription(description);
            // save the ad...
        }
    }

}
