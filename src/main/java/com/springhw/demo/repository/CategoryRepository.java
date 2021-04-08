package com.springhw.demo.repository;


import com.springhw.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
    List<Category> findByUserId(Long userId);
    //find by user id and return the category
    Category findByIdAndUserId(Long categoryId, Long userId);
    // find user by id and by category name
    Category findByUserIdAndName(Long userId, String categoryName);
}
