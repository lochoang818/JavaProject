package com.example.project.Repository;

import com.example.project.Models.Food;
import com.example.project.Models.FoodOrder;
import com.example.project.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    @Query("SELECT o FROM Orders o WHERE o.user.email = :email AND o.status = :status AND o.restaurant.resId = :restaurantId ")
    public Optional<Orders> findOrdering(String email,String status, int restaurantId);
    @Query("SELECT COUNT(o) FROM Orders o WHERE o.user.User_id = :userId AND o.restaurant.resId = :restaurantId")
    public int CountOrder(int userId, int restaurantId);



}
