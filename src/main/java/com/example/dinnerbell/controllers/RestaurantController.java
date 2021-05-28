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
        restaurantsdao.save(restaurant);
        return "redirect:/restaurant";
    }


    @GetMapping("/restaurant")
    public String restaurantProfile(Model model) {
      model.addAttribute("restaurants",restaurantsdao.findAll());
      return "business/restaurant-profile";
    }



  @GetMapping("/restaurant/details/{id}")
  public String restaurants(@PathVariable long id, Model model){
    Restaurant restaurant = restaurantsdao.getOne(id);
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User currentUser = usersdao.getOne(user.getId());
      model.addAttribute("restaurants", restaurant);
      model.addAttribute("user",currentUser);
    return "business/details";
  }

  @PostMapping("/restaurant/details/{id}")
  public String addToFavorites(@PathVariable("id") long id){
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User currentUser = usersdao.getOne(user.getId());
      Restaurant restaurant = restaurantsdao.getOne(id);
      List<User> favorites = restaurant.getFavorites();
      if (!favorites.contains(currentUser)){
        favorites.add(currentUser);
        restaurant.setFavorites(favorites);
        restaurantsdao.save(restaurant);
      }
      else{
        restaurant.setFavorites(null);
        restaurantsdao.save(restaurant);
      }
      return "redirect:/restaurant/details/{id}";
  }

    @GetMapping("/restaurant/edit/{id}")
    public String editRestaurantProfile(Model vModel, @PathVariable Long id) {
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Restaurant restaurantToEdit = restaurantsdao.getOne(id);
        vModel.addAttribute("restaurant", restaurantToEdit);
        return "business/edit-restaurant-profile";
    }

    @PostMapping("/restaurant/edit/{id}")
    public String updateRestaurantProfile(@ModelAttribute() Restaurant restaurantToEdit) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = usersdao.getOne(principal.getId());
//        restaurantToEdit
        restaurantsdao.save(restaurantToEdit);
        return "redirect:/restaurant";
    }

//    @PostMapping("/posts/update/{id}")
//    public String UpdatePostResults(@ModelAttribute("updatePost") Post post) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User creator = userDao.getOne(user.getId());
//        post.setUser(creator);
//        postDao.save(post);
//        return "redirect:/profile";
//    }



}
