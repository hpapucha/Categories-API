package com.springhw.demo.service;

import com.springhw.demo.exception.InformationExistException;
import com.springhw.demo.exception.InformationNotFoundException;
import com.springhw.demo.model.Category;
import com.springhw.demo.model.Item;
import com.springhw.demo.repository.CategoryRepository;
import com.springhw.demo.repository.ItemRepository;
import com.springhw.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public Category getCategory(Long categoryId) {
        System.out.println("service getCategory ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryRepository.findByIdAndUserId(categoryId, userDetails.getUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        } else {
            return category;
        }
    }

    public Category createCategory(Category categoryObject) {
        System.out.println("service calling createCategory ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryRepository.findByUserIdAndName(userDetails.getUser().getId(), categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        } else {
            categoryObject.setUser(userDetails.getUser());
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

    //Create Items inside item table
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

    //Gets all the items in one category
    public List<Item> getCategoryItems(Long categoryId) {
        System.out.println("service calling getCategoryItem ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category.get().getItemList();
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    //Get a single Item inside a single category
    public Item getCategoryItem(Long categoryId, Long itemId) {
        System.out.println("service calling getCategoryItem ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            Optional<Item> item = itemRepository.findByCategoryId(categoryId).stream().filter(p -> p.getId().equals(itemId)).findFirst();
            if (!item.isPresent()) {
                throw new InformationNotFoundException("item with id " + itemId + " not found");
            } else {
                return item.get();
            }
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    //Update a single Item inside a single category
    public Item updateCategoryItem(Long categoryId, Long itemId, Item itemObject) {
        System.out.println("service calling updateCategoryItem ==>");
        try {
            Item itemOne = (itemRepository.findByCategoryId(categoryId).stream().filter(p -> p.getId().equals(itemId)).findFirst()).get();
            itemOne.setName(itemObject.getName());
            itemOne.setDescription(itemObject.getDescription());
            itemOne.setDueDate(itemObject.getDueDate());
            return itemRepository.save(itemOne);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("item or category not found");
        }
    }

    //Delete a single item inside a single category

    public void deleteCategoryItem(Long categoryId, Long itemId) {
        System.out.println("service calling deleteCategoryItem ==>");
        try {
            Item item = (itemRepository.findByCategoryId(categoryId).stream().filter(p -> p.getId().equals(itemId)).findFirst()).get();
            itemRepository.deleteById(item.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("item or category not found");
        }
    }


}
