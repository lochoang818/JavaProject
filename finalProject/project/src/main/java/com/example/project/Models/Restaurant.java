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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property ="resId")
public class Restaurant {
    @Id
    @GeneratedValue
    int resId;
    String name;
    String logoUrl;
    String address;
//    @JsonIgnore
//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Category> categories;



//    @JsonIgnore
//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Orders> orders;

}
