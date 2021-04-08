package com.springhw.demo.controller;
import com.springhw.demo.model.Category;
import com.springhw.demo.model.Item;
import com.springhw.demo.repository.CategoryRepository;
import com.springhw.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

//    //When the user visits http:/localhost/9092/hello
//    @GetMapping("/api/hello")
//    public String helloWorld() {
//        return "Hello world";
//    }

    // http://localhost:9092/api/categories
    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("calling getCategories ==>");
        return categoryService.getCategories();
    }

    // http://localhost:9092/api/categories/1
    //Checking with custom error message
    @GetMapping(path = "/categories/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
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

    //Create Items inside Item table
    // http://localhost:9092/api/categories/1/item
    @PostMapping("/categories/{categoryId}/item")
    public Item createCategoryItem(@PathVariable Long categoryId, @RequestBody Item itemObject){
        System.out.println("calling createCategoryItem ==>");
       // return categoryService.createCategoryRecipe(categoryId, recipeObject);
        return categoryService.createCategoryItem(categoryId, itemObject);
    }

    // http://localhost:9092/api/categories/1/item
    //Get All Items inside a single category
    @GetMapping("/categories/{categoryId}/item")
    public List<Item> getCategoryItems(@PathVariable Long categoryId){
        System.out.println("calling getCategoryItem ==>");
        return categoryService.getCategoryItems(categoryId);
    }

    //http://localhost:9092/api/categories/1/items/1
    //Get a single Item inside a single category
    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Item getSingleCategoryItem(@PathVariable Long categoryId, @PathVariable Long itemId){
        System.out.println("calling getCategoryItem ==>");
        return categoryService.getCategoryItem(categoryId, itemId);
    }

    //Update a single item inside a single category
    //http://localhost:9092/api/categories/1/items/1
    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public Item updateCategoryItem(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "itemId") Long itemId, @RequestBody Item itemObject){
        System.out.println("calling updateCategoryItem ==>");
        return categoryService.updateCategoryItem(categoryId, itemId, itemObject);
    }

    //Delete a single item inside a single category (deleted id 3)
    //http://localhost:9092/api/categories/1/items/3
    @DeleteMapping("/categories/{categoryId}/items/{itemId}")
    public ResponseEntity<HashMap> deleteCategoryItem(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "itemId") Long itemId) {
        System.out.println("calling deleteCategoryItem ==>");
        categoryService.deleteCategoryItem(categoryId, itemId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "item with id: " + itemId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

//end
}
