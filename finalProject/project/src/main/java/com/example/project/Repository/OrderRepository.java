package com.example.project.Repository;

import com.example.project.Models.Food;
import com.example.project.Models.FoodOrder;
import com.example.project.Models.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    @Query("SELECT o FROM Orders o WHERE o.user.email = :email AND o.status = :status AND o.restaurant.resId = :restaurantId ")
    public Optional<Orders> findOrdering(String email,String status, int restaurantId);
    @Query("SELECT COUNT(o) FROM Orders o WHERE o.user.User_id = :userId AND o.restaurant.resId = :restaurantId")
    public int CountOrder(int userId, int restaurantId);

    @Modifying
    @Query("UPDATE Orders c SET c.status = 'Shipping', c.totalPrice = :totalPrice WHERE c.order_id = :order_id")
    @Transactional
    void updateOrderShipping(@Param("totalPrice") double totalPrice, @Param("order_id") String order_id);


}
