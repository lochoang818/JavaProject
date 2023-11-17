package com.example.project.Controllers;

import com.example.project.Models.Category;
import com.example.project.Models.Food;
import com.example.project.Models.Restaurant;
import com.example.project.Models.User;
import com.example.project.Repository.UserRepository;
import com.example.project.ServiceImplement.FoodServiceImp;
import com.example.project.ServiceImplement.RestaurantServiceImp;
import com.example.project.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/Restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantServiceImp;
    @Autowired
    private FoodServiceImp foodServiceImp;

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
    @GetMapping("/show/{idRes}")
    public ModelAndView food(@PathVariable Long idRes,
                             @RequestParam(value="page", defaultValue = "0") int page,
                             @RequestParam(value="up", defaultValue = "10000") Long up ,
                             @RequestParam(value="down", defaultValue = "-1") Long down,
                             @RequestParam(value="search", defaultValue = "") String search,
                             @RequestParam(value="category", defaultValue = "") String category,
                             @RequestParam(value="sortBy", defaultValue = "") String sortBy) {
        Page<Food> showResult;

        if (category.isEmpty() && search.isEmpty()) {
            System.out.println("Empty search " + search);
            category = "%";
            search = "";
            showResult = foodServiceImp.searchFoo(search, category, up, down,sortBy, Math.toIntExact(idRes),page, 6);
        } else if (!search.isEmpty()) {
            search = "%" + search + "%";
            showResult = foodServiceImp.searchFoo(search, category, up, down, sortBy,Math.toIntExact(idRes),page, 6);
        }else {
            search = "";
            System.out.println("Non-empty search " + category + search);
            showResult = foodServiceImp.searchFoo(search, category,  up, down,sortBy,Math.toIntExact(idRes),page, 6);
        }

        List<String> showResultCategory = restaurantServiceImp.getCategory(idRes);
        ModelAndView modelAndView = new ModelAndView("Restaurant/shop-grid"); // Initialize modelAndView here
        modelAndView.addObject("listCategory", showResultCategory);
        modelAndView.addObject("listFood", showResult);
        modelAndView.addObject("search", search);
        modelAndView.addObject("category", category);
        modelAndView.addObject("totalPage",showResult.getTotalPages());
        modelAndView.addObject("currentPage",page);
        modelAndView.addObject("totalItem",showResult.getTotalElements());
        return modelAndView;
    }

}
