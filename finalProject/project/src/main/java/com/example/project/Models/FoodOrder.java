package com.example.project.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FoodOrder {
    @EmbeddedId
    FoodOrderKey id;
    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "Food_id")
    Food food;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "Order_id")
    Orders order;

    int quantity;
    double price;
}
