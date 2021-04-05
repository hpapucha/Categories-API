package com.springhw.demo.repository;


import com.springhw.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<TABLE_NAME, PK>
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
}
