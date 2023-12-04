package com.example.project.ServiceImplement;

import com.example.project.Models.Food;
import com.example.project.Repository.FoodRepository;
import com.example.project.Services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImp implements FoodService {
    @Autowired
    private FoodRepository foodRepository;
     public Food findByFood_id(int foodId){
         return this.foodRepository.findByfoodId(foodId);
     }

    @Override
    public List<Food> findAll() {
        return this.foodRepository.findAll();
    }

    @Override
    public void addFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void updateFood(Food food) {
        Optional<Food> existingFoodOptional = foodRepository.findById(food.getFoodId());

        if (existingFoodOptional.isPresent()) {
            // If the food exists, update its properties
            Food existFood = existingFoodOptional.get();
            existFood.setImage(food.getImage());
            existFood.setName(food.getName());
            existFood.setPrice(food.getPrice());
            existFood.setUnit_quantity(food.getUnit_quantity());
            existFood.setCategory(food.getCategory());
            foodRepository.save(existFood);
        } else {
            // Handle the case where the food with the given ID is not found
            // You might throw an exception, log a message, or handle it in another way
            System.out.println("Food with ID " + food.getFoodId() + " not found.");
        }
    }

    @Override
    public void deleteFood(int foodId) {
        foodRepository.deleteById(foodId);
    }

    @Override
    public Page<Food> searchFoo(String query, String cat,Long up, Long down,String sortBy,int resId,int page, int pageSize) {
        Pageable p = PageRequest.of(page,pageSize);
        if(up == -1){
            up = Long.valueOf(9999999);
        }
        return  this.foodRepository.searchFoo(query,cat,up,down,sortBy,resId,p);
    }
}
