package com.example.project.Controllers;


import com.example.project.Models.Category;
import com.example.project.Models.Restaurant;
import com.example.project.Repository.CategoryRepository;
import com.example.project.Services.CategoryService;
import com.example.project.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryService categoryService;

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

    @GetMapping("/food-management")
    public String foodManagement() {
        return "Admin/food-management";
    }

    @GetMapping("/order-management")
    public String orderManagement() {
        return "Admin/order-management";
    }


}
