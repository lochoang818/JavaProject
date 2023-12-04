package com.example.project.Services;

import com.example.project.Models.Category;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryService {
    public List<Category> findAll();
    public void addCategory(Category category);
    public void updateCategory(Category category);
    public void deleteCategory(int catId);

}
