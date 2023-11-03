package com.example.project.Controllers;

import com.example.project.Models.Category;
import com.example.project.Models.Food;
import com.example.project.Models.Restaurant;
import com.example.project.ServiceImplement.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/Restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantServiceImp restaurantServiceImp;
    @GetMapping("/showAll")
    public ModelAndView index(@RequestParam(value="page", defaultValue = "0") int page,@RequestParam(value="search", defaultValue = "") String search) {
        Page<Restaurant> showResult;

        showResult= restaurantServiceImp.searchRestaurant(search,page,3);





        ModelAndView modelAndView = new ModelAndView("Restaurant/Show");
        modelAndView.addObject("listRes", showResult);
        modelAndView.addObject("search", search);

        modelAndView.addObject("totalPage",showResult.getTotalPages());
        modelAndView.addObject("currentPage",page);
        modelAndView.addObject("totalItem",showResult.getTotalElements());


        return modelAndView;
    }
    @GetMapping("/showAll/{idRes}")
    public ModelAndView index2(@PathVariable Long idRes){
        List<Category> showResultCategory = restaurantServiceImp.searchCategory(String.valueOf(idRes));
        List<Food> showResultAllFood = restaurantServiceImp.allFood();

        ModelAndView modelAndView = new ModelAndView("Restaurant/shop-grid");
        modelAndView.addObject("listCategory", showResultCategory);
        modelAndView.addObject("listAllFood", showResultAllFood);
        return modelAndView;
    }
    @GetMapping("/showAll/{idRes}/{idCate}")
    public ModelAndView index3(@PathVariable Long idRes, @PathVariable Long idCate){
        List<Category> showResultCategory = restaurantServiceImp.searchCategory(String.valueOf(idRes));
        List<Food> showResultAllFood = restaurantServiceImp.foodOfCategory(String.valueOf(idCate));

        ModelAndView modelAndView = new ModelAndView("Restaurant/shop-grid");
        modelAndView.addObject("listCategory", showResultCategory);
        modelAndView.addObject("listAllFood", showResultAllFood);
        return modelAndView;
    }




}
