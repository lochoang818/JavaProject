package com.example.project.ServiceImplement;

import com.example.project.Models.Food;
import com.example.project.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImp {
    @Autowired
    private FoodRepository foodRepository;
     public Food findByFood_id(int foodId){
         return this.foodRepository.findByfoodId(foodId);
     }
}
