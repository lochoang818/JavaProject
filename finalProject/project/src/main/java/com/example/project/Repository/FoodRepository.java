package com.example.project.Repository;

import com.example.project.Models.Food;
import com.example.project.Models.Orders;
import com.example.project.Models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Integer> {
    public Food findByfoodId(int foodId);


}
