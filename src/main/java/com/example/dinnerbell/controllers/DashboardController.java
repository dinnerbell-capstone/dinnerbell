package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.models.Review;
import com.example.dinnerbell.models.User;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.ReviewRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final RestaurantRepo restaurantsdao;
    private final UserRepo usersdao;
    private final ReviewRepo reviewRepo;



    public DashboardController(RestaurantRepo restaurantsdao, UserRepo usersdao, ReviewRepo reviewsdao) {
        this.restaurantsdao = restaurantsdao;
        this.usersdao = usersdao;
        this.reviewRepo = reviewsdao;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = usersdao.getOne(user.getId());
        List<Restaurant> userFaves = currentUser.getRestaurants();
        List<Review> userReviews = currentUser.getRestaurant().getReviews();
        model.addAttribute("user", currentUser);
        model.addAttribute("userFaves", userFaves);
        model.addAttribute("userReviews", userReviews);
        return "users/dashboard";
    }

}
