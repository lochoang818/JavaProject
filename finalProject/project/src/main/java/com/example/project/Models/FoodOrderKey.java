package com.example.project.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class FoodOrderKey implements Serializable {
    @Column(name = "food_id")
    int foodId;

    @Column(name = "order_id")
    int orderId;
}
