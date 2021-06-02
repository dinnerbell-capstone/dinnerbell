package com.example.dinnerbell.repositories;

import com.example.dinnerbell.models.Image;
import com.example.dinnerbell.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepo extends JpaRepository<Image,Long> {

  List<Image> findImageByRestaurant(Restaurant restaurant);
  Image findImageByRestaurantId(long restaurant);

}
