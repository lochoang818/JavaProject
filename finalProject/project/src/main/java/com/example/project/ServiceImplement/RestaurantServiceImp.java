package com.example.project.ServiceImplement;

import com.example.project.Models.Restaurant;
import com.example.project.Repository.RestaurantRepository;
import com.example.project.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantServiceImp implements RestaurantService {
    private RestaurantRepository restaurantRepository;
    @Autowired
    public RestaurantServiceImp(RestaurantRepository restaurantRepository){
        this.restaurantRepository=restaurantRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String query) {
        return restaurantRepository.searchRes(query);
    }
}
