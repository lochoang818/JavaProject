package com.example.project.Models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Data
@Setter
@Getter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property ="foodId")

public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int foodId;
    String image;
    String name;
    double price;
    int unit_quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;




    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    Set<FoodOrder> foodOrders;
}
