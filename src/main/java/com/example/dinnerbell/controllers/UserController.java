package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.User;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserRepo userDao;
    private final RestaurantRepo restaurantDao;
    private final PasswordEncoder passwordEncoder;

  public UserController(UserRepo userDao, RestaurantRepo restaurantDao, PasswordEncoder passwordEncoder) {
    this.userDao = userDao;
    this.restaurantDao = restaurantDao;
    this.passwordEncoder = passwordEncoder;
  }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
//      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      return "users/profile";
    }

}
