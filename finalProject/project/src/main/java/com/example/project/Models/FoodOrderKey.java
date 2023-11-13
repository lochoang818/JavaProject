package com.example.project.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class FoodOrderKey implements Serializable {
    @Column(name = "food_id")
    int foodId;

    @Column(name = "order_id")
    String orderId;
}
