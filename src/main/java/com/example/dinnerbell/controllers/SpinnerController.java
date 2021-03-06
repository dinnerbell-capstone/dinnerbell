package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.repositories.RestaurantRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class SpinnerController {
    private final RestaurantRepo restaurantdao;

    public SpinnerController(RestaurantRepo restaurantdao) {
        this.restaurantdao = restaurantdao;
    }


//    @GetMapping("/yelpRandomizer")
//    public String spinnerPage() {
//        return "business/randomSelector";
//    }

    @GetMapping("/spinner-results")
    public String spinnerResult() {
        return "app/userSelect";
    }



    @GetMapping("/about")
    public String aboutPage() {
        return "About";
    }

//    @Value("${yelp_token}")
//    private String yelpApiToken;
//
//    @RequestMapping(path = "/keys1.js", produces = "application/javascript")
//    @ResponseBody
//    public String apitoken(){
//
//        return "const yelpToken = `" + yelpApiToken + "`;";
//    }

    @GetMapping("/exclusive-pick")
    public String randomizeDbRestaurants(Model model){
        List<Restaurant> restaurants = restaurantdao.findAll();
        int randomNum = (int)(Math.random() * restaurants.size());
        model.addAttribute("restaurant", restaurants.get(randomNum));
        model.addAttribute("randomNum", "We suggest you try: " + randomNum);
        return "app/exclusives-randomization";
    }

}
