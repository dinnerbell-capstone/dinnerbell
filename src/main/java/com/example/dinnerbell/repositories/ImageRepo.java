package com.example.dinnerbell.repositories;

import com.example.dinnerbell.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image,Long> {
}
