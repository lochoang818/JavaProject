package com.example.project.Repository;

import com.example.project.Models.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    public List<Restaurant> findAll();
    @Query("SELECT c from Restaurant c WHERE c.Name LIKE CONCAT('%', :query, '%')")
    public Page<Restaurant> searchRes(String query, Pageable p);
}
