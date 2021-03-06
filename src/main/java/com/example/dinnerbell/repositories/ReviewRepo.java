package com.example.dinnerbell.repositories;

import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.models.Review;
import com.example.dinnerbell.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review,Long> {

  List<Review> findAllByRestaurant(Restaurant restaurant);
  List<Review> findAllByUser(User user);
}
