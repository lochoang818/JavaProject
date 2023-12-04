package com.example.project.ServiceImplement;

import com.example.project.Models.Category;
import com.example.project.Repository.CategoryRepository;
import com.example.project.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        Optional<Category> existingCategoryOptional = categoryRepository.findById(category.getCate_id());

        if (existingCategoryOptional.isPresent()) {
            // If the category exists, update its properties
            Category existCat = existingCategoryOptional.get();
            existCat.setName(category.getName());
            existCat.setRestaurant(category.getRestaurant());
            categoryRepository.save(existCat);
        } else {
            // Handle the case where the category with the given ID is not found
            // You might throw an exception, log a message, or handle it in another way
            System.out.println("Category with ID " + category.getCate_id() + " not found.");
        }
    }

    @Override
    public void deleteCategory(int cate_id) {
        Optional<Category> categoryOptional = categoryRepository.findById(cate_id);
        if (categoryOptional.isPresent()) {
            // If the category exists, delete it
            categoryRepository.deleteById(cate_id);
        } else {
            // Handle the case where the category with the given ID is not found
            // You might throw an exception, log a message, or handle it in another way
            System.out.println("Category with ID " + cate_id + " not found.");
        }
    }
}
