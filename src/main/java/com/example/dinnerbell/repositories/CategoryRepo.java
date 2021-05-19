package com.example.dinnerbell.repositories;

import com.example.dinnerbell.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
