package com.example.dinnerbell.repositories;

import com.example.dinnerbell.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface imageRepo extends JpaRepository<Image,Long> {
}
