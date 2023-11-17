package com.example.project.ServiceImplement;

import com.example.project.Models.Food;
import com.example.project.Repository.FoodRepository;
import com.example.project.Services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImp implements FoodService {
    @Autowired
    private FoodRepository foodRepository;
     public Food findByFood_id(int foodId){
         return this.foodRepository.findByfoodId(foodId);
     }

    @Override
    public Page<Food> searchFoo(String query, String cat,Long up, Long down,String sortBy,int resId,int page, int pageSize) {
        Pageable p = PageRequest.of(page,pageSize);
        if(up == -1){
            up = Long.valueOf(9999999);
        }
        return  this.foodRepository.searchFoo(query,cat,up,down,sortBy,resId,p);
    }
}
