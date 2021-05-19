package com.example.dinnerbell.repositories;

import com.example.dinnerbell.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
}
