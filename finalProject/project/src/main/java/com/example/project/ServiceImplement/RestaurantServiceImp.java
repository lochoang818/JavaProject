package com.example.project.ServiceImplement;

import com.example.project.Models.Category;
import com.example.project.Models.Food;
import com.example.project.Models.Restaurant;
import com.example.project.Repository.RestaurantRepository;
import com.example.project.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Restaurant> searchRestaurant(String query,int page, int pageSize) {
        Pageable p = PageRequest.of(page,pageSize);

        return  this.restaurantRepository.searchRes(query,p);
    }
    @Override
    public Page<Restaurant> getAllRestByPage(int page, int pageSize){
        Pageable p = PageRequest.of(page,pageSize);
        return  this.restaurantRepository.findAll(p);
    }
    @Override
    public List<Category> searchCategory(String query) {
        return restaurantRepository.searchCategory(query);
    }

    @Override
    public List<Food> allFood() { return restaurantRepository.allFood(); }
    @Override
    public List<Food> foodOfCategory(String query) {
        return restaurantRepository.foodOfCategory(query);
    }
}