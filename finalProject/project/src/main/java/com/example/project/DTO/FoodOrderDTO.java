package com.example.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class FoodOrderDTO {
    private int foodId;
    private String foodName;
    private String imageUrl;
    private double foodPrice;
    private int quantity;
    private double orderPrice;

    public FoodOrderDTO(int foodId, String foodName, String imageUrl, double foodPrice, int quantity, double orderPrice) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.imageUrl = imageUrl;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }
// Các phương thức getter và setter
}