package com.springhw.demo.controller;
import com.springhw.demo.exception.InformationExistException;
import com.springhw.demo.exception.InformationNotFoundException;
import com.springhw.demo.model.Category;
import com.springhw.demo.model.Item;
import com.springhw.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class Controller {

    private CategoryRepository categoryRepository;
    //dependency injection use interface without getters/setters
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //When the user visits http:/localhost/9092/hello
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello world";
    }

    // http://localhost:9092/api/categories
    @GetMapping("/categories")
    public List<Category> getCategories(){
        System.out.println("calling getCategories ==>");
        return categoryRepository.findAll();
    }

    // http://localhost:9092/api/categories/1
    //Checking with custom error message
    @GetMapping(path = "/categories/{categoryId}")
    public Optional getCategory(@PathVariable Long categoryId) {
        System.out.println("calling getCategory ==>");
        Optional category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            return category;
        }
        else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    // http://localhost:PORT-NUMBER/api/categories/
    @PostMapping(path = "/categories")
    public Category createCategory(@RequestBody Category categoryObject){
        System.out.println("calling createCategory ==>");
        Category category = categoryRepository.findByName(categoryObject.getName());
        if(category !=null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        }else {
            return categoryRepository.save(categoryObject);
        }
    }

    // http://localhost:PORT-NUMBER/api/categories/1
    @PutMapping(path = "/categories/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category categoryObject) {
        System.out.println("calling updateCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            if (categoryObject.getName().equals(category.get().getName())) {
                throw new InformationExistException("category with name " + category.get().getName() + " already exists");
            } else {
                Category updateCategory = categoryRepository.findById(categoryId).get();
                updateCategory.setName(categoryObject.getName());
                updateCategory.setDescription(categoryObject.getDescription());
                return categoryRepository.save(updateCategory);
            }
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    // http://localhost:PORT-NUMBER/api/categories/1
    @DeleteMapping(path = "/categories/{categoryId}")
    public Optional<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }

    }

    // http://localhost:PORT-NUMBER/api/categories/1/recipes
    @PostMapping("/categories/{categoryId}/recipes")
    public Item createCategoryRecipe(@PathVariable Long categoryId, @RequestBody Item recipeObject){
        System.out.println("calling createCategoryRecipe ==>");
       // return categoryService.createCategoryRecipe(categoryId, recipeObject);
        return null;
    }

}
