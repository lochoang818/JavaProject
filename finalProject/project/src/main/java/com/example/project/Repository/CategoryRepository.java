package com.example.project.Repository;

import com.example.project.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //query to find category by id
    @Query("SELECT c from Category c WHERE c.cate_id = :cate_id")
    public Category findByCate_id(int cate_id);


}
