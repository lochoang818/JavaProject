package com.example.project.Controllers;


import com.example.project.Models.Category;
import com.example.project.Models.Food;
import com.example.project.Models.Orders;
import com.example.project.Models.Restaurant;
import com.example.project.Repository.CategoryRepository;
import com.example.project.Services.CategoryService;
import com.example.project.Services.FoodService;
import com.example.project.Services.OrderService;
import com.example.project.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/restaurant-management")
    public String restaurantManagement(Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        List<Restaurant> restaurants = restaurantService.findAll();
//        int start = (page - 1) * 5;
//        int end = Math.min(start + 5, restaurants.size());
//        model.addAttribute("restaurants", restaurants.subList(start, end));

        model.addAttribute("restaurants", restaurants);
        model.addAttribute("newRestaurant", new Restaurant());

        return "Admin/restaurant_management";
    }


    @PostMapping("/restaurant-management/add")
    public String addRestaurant(@ModelAttribute Restaurant newRestaurant, Model model) {
        restaurantService.addRestaurant(newRestaurant);
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        return "redirect:/restaurant-management";
    }


//        @PostMapping("/restaurant-management/edit")
//        public ResponseEntity<List<Restaurant>> editRestaurant(@ModelAttribute Restaurant newRestaurant, Model model) {
//            restaurantService.updateRestaurant(newRestaurant);
//            List<Restaurant> restaurants = restaurantService.findAll();
//            model.addAttribute("restaurants", restaurants);
//            return ResponseEntity.ok(restaurants);
//        }

    //    @RequestMapping(value = "/restaurant-management/edit", method = RequestMethod.POST)
    @PostMapping("/restaurant-management/edit")
    public String editRestaurantReloadPage(@ModelAttribute Restaurant newRestaurant, Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        restaurantService.updateRestaurant(newRestaurant);
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        return "redirect:/restaurant-management";
    }

    @PostMapping("/restaurant-management/delete")
    public String deleteRestaurant(@RequestParam(name = "resId") int resId, Model model) {
        restaurantService.deleteRestaurant(resId);
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        return "redirect:/restaurant-management";
    }

    @GetMapping("/category-management")
    public String categoryManagement(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("newCategory", new Category());
        model.addAttribute("restaurants", restaurants);

        return "Admin/category-management";
    }

    @PostMapping("/category-management/add")
    public String addCategory(@ModelAttribute Category newCategory, Model model) {
        categoryService.addCategory(newCategory);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "redirect:/category-management";
    }

    @PostMapping("/category-management/edit")
    public String editCategory(@ModelAttribute Category newCategory, Model model) {
        categoryService.updateCategory(newCategory);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "redirect:/category-management";
    }

    @PostMapping("/category-management/delete")
    public String deleteCategory(@RequestParam(name = "cate_id") int cate_id, Model model) {
        categoryService.deleteCategory(cate_id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "redirect:/category-management";
    }

    @GetMapping("/food-management")
    public String foodManagement(Model model) {
        List<Food> foods = foodService.findAll();
        List<Category> categories = categoryService.findAll();

        model.addAttribute("foods", foods);
        model.addAttribute("newFood", new Food());
        model.addAttribute("categories", categories);

        return "Admin/food-management";

    }

    @PostMapping("/food-management/add")
    public String addFood(@ModelAttribute Food newFood, Model model) {
        foodService.addFood(newFood);
        List<Food> foods = foodService.findAll();
        model.addAttribute("foods", foods);
        return "redirect:/food-management";
    }

    @PostMapping("/food-management/edit")
    public String editFood(@ModelAttribute Food newFood, Model model) {
        foodService.updateFood(newFood);
        List<Food> foods = foodService.findAll();
        model.addAttribute("foods", foods);
        return "redirect:/food-management";
    }

    @PostMapping("/food-management/delete")
    public String deleteFood(@RequestParam(name = "foodId") int foodId, Model model) {
        foodService.deleteFood(foodId);
        List<Food> foods = foodService.findAll();
        model.addAttribute("foods", foods);
        return "redirect:/food-management";
    }

    @GetMapping("/order-management")
    public String orderManagement(Model model) {
        List<Orders> orders = orderService.findAll();
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("orders", orders);
        model.addAttribute("newOrder", new Orders());
        return "Admin/order-management";
    }

    @PostMapping("/order-management/edit")
    public String editOrder(@ModelAttribute Orders newOrder, Model model) {
        orderService.updateOrder(newOrder);
        List<Orders> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "redirect:/order-management";
    }

    @PostMapping("/order-management/delete")
    public String deleteOrder(@RequestParam(name = "order_id") String order_id, Model model) {
        orderService.deleteOrder(order_id);
        List<Orders> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "redirect:/order-management";
    }


}
