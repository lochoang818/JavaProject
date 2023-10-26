package com.example.project.Services;

import com.example.project.Models.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantService {
    public List<Restaurant> findAll();
    public List<Restaurant> searchRestaurant(String query);
}
