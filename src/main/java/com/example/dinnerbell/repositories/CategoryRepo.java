package com.example.dinnerbell.repositories;

import com.example.dinnerbell.models.RestaurantCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantCategoryRepo extends JpaRepository<RestaurantCategory, Long> {
}
