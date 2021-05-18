package com.example.dinnerbell.repositories;

import com.example.dinnerbell.models.FavoriteRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRestaurantRepo extends JpaRepository<FavoriteRestaurant, Long> {
}
