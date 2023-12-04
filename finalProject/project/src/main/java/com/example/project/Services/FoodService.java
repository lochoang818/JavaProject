package com.example.project.Services;

import com.example.project.Models.Food;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FoodService {

    public List<Food> findAll();
    public void addFood(Food food);
    public void updateFood(Food food);
    public void deleteFood(int foodId);

    public Page<Food> searchFoo(String query, String cat, Long up, Long down, String sortBy,int resId,int page, int pageSize);
}
