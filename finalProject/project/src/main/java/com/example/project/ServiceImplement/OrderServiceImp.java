package com.example.project.ServiceImplement;

import com.example.project.DTO.FoodOrderDTO;
import com.example.project.Models.*;
import com.example.project.Repository.FoodOrderRepository;
import com.example.project.Repository.OrderRepository;
import com.example.project.Repository.RestaurantRepository;
import com.example.project.Repository.UserRepository;
import com.example.project.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FoodOrderRepository foodOrderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<FoodOrderDTO> foodCartList(String email, int resId) {
        Restaurant r = restaurantRepository.findByResId(resId);
        Optional<Orders> response = orderRepository.findOrdering(email, "Ordering", resId);
        return foodOrderRepository.findFoodOrderDetailsByResId(r.getResId(), response.get().getOrder_id());
    }

    public Double totalPriceCart(String email, int resId) {
        Restaurant r = restaurantRepository.findByResId(resId);
        Optional<Orders> response = orderRepository.findOrdering(email, "Ordering", resId);
        return this.foodOrderRepository.totalPriceCart(response.get().getOrder_id(), r.getResId());
    }

    public void AddFoodToCart(Food f, Orders o) {
        Optional<FoodOrder> existing = foodOrderRepository.existingFoodOrder(f.getFoodId(), o.getOrder_id());

        if (existing.isPresent()) {
            FoodOrder foodOrder = existing.get();
            foodOrder.setQuantity(foodOrder.getQuantity() + 1);
            foodOrder.setPrice(f.getPrice() + existing.get().getPrice());

            foodOrderRepository.save(foodOrder);
        } else {
            FoodOrderKey foodOrderKey = new FoodOrderKey();
            foodOrderKey.setFoodId(f.getFoodId()); // Gán giá trị foodId từ đối tượng Food
            foodOrderKey.setOrderId(o.getOrder_id()); // Gán giá trị orderId từ đối tượng Orders

            FoodOrder foodOrder = new FoodOrder();
            foodOrder.setId(foodOrderKey);
            foodOrder.setOrder(o);
            foodOrder.setFood(f);
            foodOrder.setQuantity(1);
            foodOrder.setPrice(f.getPrice());

            foodOrderRepository.save(foodOrder);

        }
    }

    public void insertCart(String email, int resId) {
        Optional<Orders> response = orderRepository.findOrdering(email, "Ordering", resId);
        User user = this.userRepository.findByEmail(email);
        Restaurant r = restaurantRepository.findByResId(resId);

        if (response.isEmpty()) {
            int index = orderRepository.CountOrder(user.getUser_id(), resId) + 1;
            String newId = email + resId + index;
            Orders o = new Orders();
            o.setOrder_id(newId);
            o.setAddress(user.getAddress());
            o.setStatus("Ordering");
            o.setPhone(user.getPhone());
            o.setTotalPrice(0);
            o.setRestaurant(r);
            o.setUser(user);
            orderRepository.save(o);
        } else {
        }
    }

    public Optional<Orders> findOrdering(String email, int restaurantId) {
        return this.orderRepository.findOrdering(email, "Ordering", restaurantId);
    }

    public List<Restaurant> CartList(String email) {
        return this.foodOrderRepository.CartList(email);
    }

    @Override
    public void updateQuantity(int newQuantity, int foodId, double price) {
        foodOrderRepository.updateCart(newQuantity, foodId, price);
    }

    @Override
    public void deleteCart(int foodId) {
        foodOrderRepository.deleteCart(foodId);
    }


}
