package com.springhw.demo.model;

import javax.persistence.*;
import java.util.List;

//Entity means this is going to be a table
@Entity
@Table(name="categories")
public class Category {

    //Only the id should have generated value because its primary key
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    //Mapping to category private in item.java
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<Item> itemList;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", " +
                "name='" + name + '\'' + ", " +
                "description='" + description + '\'' + '}';
    }

    public List<Item> getRecipeList() { return itemList; }

    public void setRecipeList(List<Item> recipeList) { this.itemList = recipeList; }
}
