package com.example.project.Services;

import com.example.project.Models.Food;
import org.springframework.data.domain.Page;

public interface FoodService {

    public Page<Food> searchFoo(String query, String cat, Long up, Long down, String sortBy,int resId,int page, int pageSize);
}
