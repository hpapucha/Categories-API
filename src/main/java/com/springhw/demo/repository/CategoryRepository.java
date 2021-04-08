package com.springhw.demo.repository;


import com.springhw.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
    List<Category> findByUserId(Long userId);
}
