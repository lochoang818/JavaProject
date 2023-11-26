package com.example.project.Repository;

import com.example.project.Models.Food;
import com.example.project.Models.Orders;
import com.example.project.Models.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodRepository extends JpaRepository<Food,Integer> {
    public Food findByfoodId(int foodId);

    @Query("SELECT c FROM Food c JOIN c.category cat WHERE c.category.restaurant.resId =:resId AND cat.Name LIKE :cat AND (c.price <= :up AND c.price >= :down) " +
            "OR c.name LIKE :query AND (c.price <= :up AND c.price >= :down) ORDER BY " +
            "CASE WHEN :sortBy = 'NameASC' THEN c.name END ASC, " +
            "CASE WHEN :sortBy = 'NameDESC' THEN c.name END DESC, " +
            "CASE WHEN :sortBy = 'PriceASC' THEN c.price END ASC, " +
            "CASE WHEN :sortBy = 'PriceDESC' THEN c.price END DESC")
    public Page<Food> searchFoo(@Param("query") String query, @Param("cat") String cat, @Param("up") Long up, @Param("down") Long down,
                                @Param("sortBy") String sortBy, int resId,Pageable p);

}
