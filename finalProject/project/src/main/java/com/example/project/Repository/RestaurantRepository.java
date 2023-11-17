package com.example.project.Repository;

import com.example.project.Models.Category;
import com.example.project.Models.Food;
import com.example.project.Models.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    public List<Restaurant> findAll();
    @Query("SELECT c from Restaurant c WHERE c.name LIKE CONCAT('%', :query, '%')")
    public Page<Restaurant> searchRes(String query, Pageable p);
    @Query("SELECT c from Category c WHERE c.restaurant.resId = :query")
    public List<Category> searchCategory(String query);
    @Query("SELECT c from Food c where c.category.restaurant.resId = :resId ")
    public List<Food> allFood(int resId);
    @Query("SELECT c from Food c WHERE c.category.cate_id= :query")
    public List<Food> foodOfCategory(String query);

    public Restaurant findByResId(int resId);

    @Query("SELECT c.Name from Category c WHERE c.restaurant.resId =:resId")
    public List<String> getCategory(Long resId);
}
