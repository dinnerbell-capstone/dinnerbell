package com.example.dinnerbell.repositories;

import com.example.dinnerbell.models.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantReviewRepo extends JpaRepository<RestaurantReview,Long> {
}
