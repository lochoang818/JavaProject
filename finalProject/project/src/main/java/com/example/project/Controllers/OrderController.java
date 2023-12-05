package com.example.project.Controllers;

import com.example.project.DTO.FoodOrderDTO;
import com.example.project.Models.*;
import com.example.project.Repository.FoodRepository;
import com.example.project.Repository.RestaurantRepository;
import com.example.project.Repository.UserRepository;
import com.example.project.ServiceImplement.FoodServiceImp;
import com.example.project.ServiceImplement.OrderServiceImp;
import com.example.project.ServiceImplement.RestaurantServiceImp;
import com.example.project.ServiceImplement.UserServiceImp;
import com.example.project.Services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }
    }

    @GetMapping("/getOrder")
    public ResponseEntity getOrder(@RequestParam("ResId") int ResId, @RequestParam("email") String email, @RequestParam("foodId") int foodId) {
        try {


            List<Restaurant> o = this.orderService.CartList(email);
            return (ResponseEntity) ResponseEntity.ok(o);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @GetMapping("/ShowCart/{idRes}")
    public ModelAndView ShowCart(@PathVariable(name = "idRes") Integer ResId, HttpSession session) {
        ModelAndView modelAndView;

        String email = (String) session.getAttribute("email");

        if (email == null) {
            modelAndView = new ModelAndView("redirect:/signin");
        } else {
            modelAndView = new ModelAndView("Orders/Cart");
            System.out.println(email);
            List<FoodOrderDTO> lstFood = this.orderService.foodCartList(email, ResId);
            modelAndView.addObject("ResId", ResId);
            modelAndView.addObject("listFood", lstFood);
            Double totalPrice = this.orderService.totalPriceCart(email, ResId);
            modelAndView.addObject("TotalPrice", totalPrice);
        }

        return modelAndView;
    }

    @GetMapping("/AddFoodToCart/{idRes}/{idFood}")
    public ModelAndView AddFoodToCart(@PathVariable(name = "idRes") Integer ResId, @PathVariable(name = "idFood") Integer foodId, HttpSession session) {
        ModelAndView modelAndView;

        String email = (String) session.getAttribute("email");

        if (email == null) {
            modelAndView = new ModelAndView("redirect:/signin");
        } else {
            modelAndView = new ModelAndView("Orders/Cart");
            orderService.insertCart(email, ResId);

            Optional<Orders> o = this.orderService.findOrdering(email, ResId);
            if (o.isPresent()) {
                Food f = this.foodRepository.findByfoodId(foodId);
                this.orderService.AddFoodToCart(f, o.get());
            }

            List<FoodOrderDTO> lstFood = this.orderService.foodCartList(email, ResId);
            modelAndView.addObject("ResId", ResId);
            modelAndView.addObject("listFood", lstFood);
            Double totalPrice = this.orderService.totalPriceCart(email, ResId);
            modelAndView.addObject("TotalPrice", totalPrice);
        }

        return modelAndView;
    }

    @GetMapping("/CartList")
    public ModelAndView CartList(HttpSession session) {
        ModelAndView modelAndView;

        String email = (String) session.getAttribute("email");


        if (email == null) {
            modelAndView = new ModelAndView("redirect:/signin");
        } else {
            System.out.println(email);
            modelAndView = new ModelAndView("Orders/CartList");

            List<Restaurant> r = this.orderService.CartList(email);
            modelAndView.addObject("cartlist", r);
        }

        return modelAndView;
    }

    @PostMapping("/updateQuantity/{foodId}/{newQuantity}")
    public ResponseEntity<String> updateQuantity(@PathVariable int foodId, @PathVariable int newQuantity, @RequestParam double price, HttpSession session) {
        String email = (String) session.getAttribute("email");


        if (email != null) {
            // Update quantity in the database
            double priceNew = price * newQuantity;
            orderService.updateQuantity(newQuantity, foodId, priceNew);
            return ResponseEntity.ok("Quantity updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }

    @PostMapping("/deleteCartItem/{foodId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable int foodId, HttpSession session) {
        // Get the email from the session or any other way you authenticate users
        String email = (String) session.getAttribute("email");
        if (email != null) {
            try {
                orderService.deleteCart(foodId);
                return ResponseEntity.ok("Item deleted successfully");

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting item");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }
    @GetMapping("/OrderHistory")
    public ModelAndView orderHistory(HttpSession session){
        ModelAndView modelAndView;

        String email = (String) session.getAttribute("email");


        if (email == null) {
            modelAndView = new ModelAndView("redirect:/signin");
        } else {
            modelAndView = new ModelAndView("Orders/OrderHistory");

            List<Orders> r = this.orderService.getAllOrder(email);
            modelAndView.addObject("allOrder", r);
        }

        return modelAndView;
    }
    @GetMapping("/DetailOrder/{idOrder}")
    public ModelAndView ShowDetailOrder(@PathVariable(name = "idOrder") String idOrder, HttpSession session) {
        ModelAndView modelAndView;

        String email = (String) session.getAttribute("email");

        if (email == null) {
            modelAndView = new ModelAndView("redirect:/signin");
        } else {
            modelAndView = new ModelAndView("Orders/OrderDetail");
            List<FoodOrder> lstFood = this.orderService.getDetailOrder(idOrder);
            Orders o = this.orderService.getOrder(idOrder);
            modelAndView.addObject("lstFood", lstFood);
            modelAndView.addObject("orderDetail", o);

        }

        return modelAndView;
    }
}
