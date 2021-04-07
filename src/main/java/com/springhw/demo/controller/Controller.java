package com.springhw.demo.controller;
import com.springhw.demo.exception.InformationExistException;
import com.springhw.demo.exception.InformationNotFoundException;
import com.springhw.demo.model.Category;
import com.springhw.demo.model.Item;
import com.springhw.demo.repository.CategoryRepository;
import com.springhw.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class Controller {

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    //dependency injection use interface without getters/setters
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
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
        return categoryService.getCategories();
    }

    // http://localhost:9092/api/categories/1
    //Checking with custom error message
    @GetMapping(path = "/categories/{categoryId}")
    public Optional getCategory(@PathVariable Long categoryId) {
        System.out.println("calling getCategory ==>");
        return categoryService.getCategory(categoryId);
    }

    // http://localhost:PORT-NUMBER/api/categories/
    @PostMapping(path = "/categories")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("calling createCategory ==>");
        return categoryService.createCategory(categoryObject);
    }

    // http://localhost:PORT-NUMBER/api/categories/1
    @PutMapping(path = "/categories/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category categoryObject) {
        System.out.println("calling updateCategory ==>");
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    @DeleteMapping("/categories/{categoryId}")
    public Optional<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        return categoryService.deleteCategory(categoryId);
    }

    //ITEM
    // http://localhost:PORT-NUMBER/api/categories/1/item
    @PostMapping("/categories/{categoryId}/recipes")
    public Item createCategoryRecipe(@PathVariable Long categoryId, @RequestBody Item recipeObject){
        System.out.println("calling createCategoryRecipe ==>");
       // return categoryService.createCategoryRecipe(categoryId, recipeObject);
        return null;
    }

}
