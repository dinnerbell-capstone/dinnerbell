package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.models.User;
import com.example.dinnerbell.repositories.RestaurantRepo;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, @RequestParam(name = "isBusiness", required = false, defaultValue = "false") boolean isBusiness) {
        user.setIsBusiness(isBusiness);
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }




    @GetMapping("/users/edit")
    public String editUserProfile(Model vModel) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToEdit = userDao.getOne(principal.getId());
        vModel.addAttribute("user", userToEdit);
        return "users/edit-user-profile";
    }

//    UPDATES USER INFO IN DB, RETURNS TO PROFILE
    @PostMapping("/users/edit")
    public String updateUserProfile(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "email") String email,
                                    User userToEdit) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.getOne(principal.getId());
      if (currentUser.getIsBusiness()) {
        userToEdit.setId(principal.getId());
        userToEdit.setRestaurant(currentUser.getRestaurant());
        userToEdit.setIsBusiness(currentUser.getIsBusiness());
        userToEdit.setUsername(username);
        userToEdit.setEmail(email);
        userDao.save(userToEdit);
        return "redirect:/restaurant";
      }
        userToEdit.setId(principal.getId());
        userToEdit.setUsername(username);
        userToEdit.setEmail(email);
        userDao.save(userToEdit);
        return "redirect:/dashboard";
    }

}
