package com.example.project.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Food_id;
    String image;
    String name;
    double price;
    int unit_quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<FoodOrder> foodOrders;
}
