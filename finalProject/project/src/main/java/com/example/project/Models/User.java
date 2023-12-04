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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property ="user_id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int User_id;
    String address;
    String name;
    String email;
    String phone;
    String password;
    String role;
    boolean enable;
    String verificationCode;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Orders> orders;
}
