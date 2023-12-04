package com.example.project.Services;

import com.example.project.DTO.FoodOrderDTO;
import com.example.project.Models.*;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<FoodOrderDTO> foodCartList(String email, int resId);
    public Double totalPriceCart( String email,int resId);

    public void AddFoodToCart(Food f, Orders o);

    public void insertCart(String email, int resId);
    public Optional<Orders> findOrdering(String email, int restaurantId);
    public void updateOrderShipping(String order_id, double totalPrice);
    public List<Restaurant> CartList(String email);

    public void updateQuantity(int newQuantity, int foodId, double price);
    public void deleteCart(int foodId);
    public List<Orders> getAllOrder(String email);
    public List<FoodOrder> getDetailOrder(String orderId);
    public Orders getOrder(String id);

}
