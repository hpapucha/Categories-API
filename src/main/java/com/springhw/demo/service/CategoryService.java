package com.springhw.demo.service;

import com.springhw.demo.exception.InformationExistException;
import com.springhw.demo.exception.InformationNotFoundException;
import com.springhw.demo.model.Category;
import com.springhw.demo.model.Item;
import com.springhw.demo.repository.CategoryRepository;
import com.springhw.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Autowired
    public void setItemRepository(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<Category> getCategories() {
        System.out.println("service calling getCategories ==>");
        return categoryRepository.findAll();
    }

    //@Pathvariable only applies to the url
    public Optional getCategory(Long categoryId) {
        System.out.println("service calling getCategory ==>");
        Optional category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public Category createCategory(Category categoryObject) {
        System.out.println("service calling createCategory ==>");
        Category category = categoryRepository.findByName(categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }

    public Category updateCategory(Long categoryId, Category categoryObject) {
        System.out.println("service calling updateCategory ==>");
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

    public Optional<Category> deleteCategory(Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public Item createCategoryItem(Long categoryId, Item itemObject){
        System.out.println("service calling createCategoryItem ==>");
        try{
            Optional category = categoryRepository.findById(categoryId);
            //we are setting the category for the recipe and casting the datatype
            //to be the Category type
            itemObject.setCategory((Category) category.get());
            return itemRepository.save(itemObject);
        } catch(NoSuchElementException e) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }
}
