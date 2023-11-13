package com.example.project.Controllers;

import com.example.project.DTO.FoodOrderDTO;
import com.example.project.Models.Food;
import com.example.project.Models.Orders;
import com.example.project.Models.Restaurant;
import com.example.project.Models.User;
import com.example.project.Repository.FoodRepository;
import com.example.project.Repository.RestaurantRepository;
import com.example.project.Repository.UserRepository;
import com.example.project.ServiceImplement.FoodServiceImp;
import com.example.project.ServiceImplement.OrderServiceImp;
import com.example.project.ServiceImplement.RestaurantServiceImp;
import com.example.project.ServiceImplement.UserServiceImp;
import com.example.project.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodRepository foodRepository;
    @GetMapping("/getOrder")
    public ResponseEntity getOrder(@RequestParam("ResId") int ResId, @RequestParam("email") String email,@RequestParam("foodId") int foodId) {
        try {


            List<FoodOrderDTO> o = this.orderService.foodCartList(email,ResId);
            return (ResponseEntity) ResponseEntity.ok(o);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }



    @GetMapping("/AddFoodToCart/{idRes}/{idFood}")
    public ModelAndView AddFoodToCart(@PathVariable(name = "idRes") Integer ResId, @PathVariable(name = "idFood") Integer foodId ){
        ModelAndView modelAndView = new ModelAndView("Orders/Cart");
        String email = "lochoang611@gmail.com";

        orderService.insertCart(email,ResId);

            Optional<Orders> o = this.orderService.findOrdering(email,ResId);
            if(o.isPresent()){
                Food f = this.foodRepository.findByfoodId(foodId);
                this.orderService.AddFoodToCart(f,o.get());

            }
        List<FoodOrderDTO> lstFood = this.orderService.foodCartList(email,ResId);
        modelAndView.addObject("ResId", ResId);

        modelAndView.addObject("listFood", lstFood);
        Double totalPrice = this.orderService.totalPriceCart(email,ResId);
        modelAndView.addObject("TotalPrice", totalPrice);

        return modelAndView;
    }
}
