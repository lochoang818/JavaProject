package com.example.project.Repository;

import com.example.project.DTO.FoodOrderDTO;
import com.example.project.Models.Food;
import com.example.project.Models.FoodOrder;
import com.example.project.Models.FoodOrderKey;
import com.example.project.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, FoodOrderKey> {
    @Query("SELECT o FROM FoodOrder o WHERE o.food.foodId = :foodId AND o.order.order_id = :orderId")
    Optional<FoodOrder> existingFoodOrder(@Param("foodId") int foodId, @Param("orderId") String orderId);
    @Query("SELECT new com.example.project.DTO.FoodOrderDTO(f.foodId, f.name, f.image, f.price, fo.quantity, fo.price) " +
            "FROM FoodOrder fo " +
            "JOIN fo.food f " +
            "JOIN f.category c " +
            "JOIN c.restaurant r " +
            "WHERE r.resId = :resId and fo.order.order_id like :orderId")
    List<FoodOrderDTO> findFoodOrderDetailsByResId( int resId, String orderId);
    @Query("select  sum(f.price) from  FoodOrder  f where  f.order.order_id like :orderId and f.food.category.restaurant.resId = :resId")
    Double totalPriceCart( String orderId, int resId);


}
