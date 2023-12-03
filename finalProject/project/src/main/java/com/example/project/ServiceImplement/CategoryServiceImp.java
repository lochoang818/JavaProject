package com.example.project.ServiceImplement;

import com.example.project.Models.Category;
import com.example.project.Repository.CategoryRepository;
import com.example.project.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

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
        Category existCat = categoryRepository.findByCate_id(category.getCate_id());
        existCat.setName(category.getName());
        existCat.setRestaurant(category.getRestaurant());
        categoryRepository.save(existCat);
    }

    @Override
    public void deleteCategory(int catId) {
        categoryRepository.deleteById(catId);
    }
}
