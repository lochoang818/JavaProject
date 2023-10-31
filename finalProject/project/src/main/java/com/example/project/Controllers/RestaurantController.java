package com.example.project.Controllers;

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
    @GetMapping("/show")
    public ModelAndView index(@RequestParam(value="page", defaultValue = "0") int page,@RequestParam(value="search", required = false) String search) {
        Page<Restaurant> showResult;
        if(search!=null){
            showResult= restaurantServiceImp.searchRestaurant(search,page,3);
        }
        else {
            showResult = restaurantServiceImp.getAllRestByPage(page,3);

        }

        ModelAndView modelAndView = new ModelAndView("Restaurant/Show");
        modelAndView.addObject("listRes", showResult);
        modelAndView.addObject("totalPage",showResult.getTotalPages());
        modelAndView.addObject("currentPage",page);
        modelAndView.addObject("totalItem",showResult.getTotalElements());


        return modelAndView;
    }





}
