package com.example.project.Models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property ="cate_id")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cate_id;
    String Name;
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Food> foodList;
    @ManyToOne
    @JoinColumn(name = "restaurant")

    private Restaurant restaurant;
}
