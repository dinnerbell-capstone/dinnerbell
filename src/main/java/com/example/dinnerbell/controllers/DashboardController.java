package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.models.User;
import com.example.dinnerbell.repositories.CategoryRepo;
import com.example.dinnerbell.repositories.ImageRepo;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final RestaurantRepo restaurantsdao;

    private final UserRepo usersdao;
    private final ImageRepo imageDao;

    @Value("${file_upload_path}")
    private String uploadPath;

    public DashboardController(RestaurantRepo restaurantsdao, UserRepo usersdao, ImageRepo imageDao) {
        this.restaurantsdao = restaurantsdao;
        this.usersdao = usersdao;
        this.imageDao = imageDao;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = usersdao.getOne(user.getId());
//        Restaurant restaurant = restaurantsdao.getOne(currentUser.getRestaurant().getId());
        List<Restaurant> userFaves = currentUser.getRestaurants();
        model.addAttribute("user", currentUser);
        model.addAttribute("userFaves", userFaves);
        return "users/dashboard";
    }

}
