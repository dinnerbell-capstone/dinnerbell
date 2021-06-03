package com.example.dinnerbell.controllers;

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
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/dashboard";
    }

    @GetMapping("/profile")
    public String restaurants(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = usersdao.getOne(user.getId());
        model.addAttribute("user",currentUser);
        System.out.println(currentUser.getUsername());
        return "users/profile";
    }

}
