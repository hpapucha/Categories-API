package com.springhw.demo.controller;


import com.springhw.demo.model.Category;
import com.springhw.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    private CategoryRepository categoryRepository;

    //dependency injection use interface without getters/setters
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    //When the user visits http:/localhost/9092/hello
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    // http://localhost:9092/api/categories
    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("calling getCategories ==>");
        return categoryRepository.findAll();
    }
}
