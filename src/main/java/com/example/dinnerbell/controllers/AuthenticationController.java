package com.example.dinnerbell.controllers;

import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {
  private final UserRepo userDao;

  public AuthenticationController(UserRepo userDao) {
    this.userDao = userDao;
  }

  @GetMapping("/login")
  public String showLoginForm() {
    return "users/login";
  }

  @PostMapping("/login")
  public String login() {
    return "redirect:/dashboard";
  }

}
