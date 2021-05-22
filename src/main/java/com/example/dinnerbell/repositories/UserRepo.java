package com.example.dinnerbell.repositories;

import com.example.dinnerbell.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
