package com.example.project.Services;

import com.example.project.Models.Category;
import com.example.project.Models.Food;
import com.example.project.Models.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantService {
    public List<Restaurant> findAll();
    public Page<Restaurant> searchRestaurant(String query,int page, int pageSize);
    public Page<Restaurant> getAllRestByPage(int page, int pageSize);
    public List<Category> searchCategory(String query);

    public List<Food> allFood(int resId);

    public List<Food> foodOfCategory(String query);
    public Restaurant findRestaurantByRes_id(int res_id);

    public List<String> getCategory(Long resId);

}
