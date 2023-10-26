package com.example.project.Controllers;

import com.example.project.Models.Restaurant;
import com.example.project.ServiceImplement.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView index() {
        List<Restaurant> showResult = restaurantServiceImp.findAll();
        ModelAndView modelAndView = new ModelAndView("Restaurant/Show");
        modelAndView.addObject("listRes", showResult);
        return modelAndView;
    }
    public static class SearchRequest {
        private String query;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }
    }

    @PostMapping("/searchRestaurant")
    public ResponseEntity<List<Restaurant>> getRestaurant(@RequestBody SearchRequest searchRequest) {
        // Giả sử bạn có một danh sách sản phẩm
        List<Restaurant> restaurants = this.restaurantServiceImp.searchRestaurant(searchRequest.getQuery());

        // Trả về danh sách sản phẩm dưới dạng JSON
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


}
