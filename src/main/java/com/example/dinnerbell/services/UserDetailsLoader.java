package com.example.dinnerbell.services;

import com.example.dinnerbell.models.User;
import com.example.dinnerbell.models.UserWithRoles;
import com.example.dinnerbell.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsLoader implements UserDetailsService {
  private final UserRepo users;

  public UserDetailsLoader(UserRepo users) {
    this.users = users;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = users.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("No user found for " + username);
    }

    return new UserWithRoles(user);
  }
  }
}
