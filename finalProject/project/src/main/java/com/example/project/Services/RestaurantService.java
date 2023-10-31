package com.example.project.Services;

import com.example.project.Models.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantService {
    public List<Restaurant> findAll();
    public Page<Restaurant> searchRestaurant(String query,int page, int pageSize);
    public Page<Restaurant> getAllRestByPage(int page, int pageSize);
}
