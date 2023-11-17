package com.example.project.Controllers;

import com.example.project.Models.User;
import com.example.project.Repository.UserRepository;
import com.example.project.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserService userService;
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
    @GetMapping("/")
    public String index() {
        return "Home/homePage";
    }


    @GetMapping("/register")
    public String register() {
        return "landingPage/landingPage";
    }

    @GetMapping("/signin")
    public String login() {
        return "landingPage/landingPage";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "landingPage/landingPage";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session, Model m, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        url = url.replace(request.getServletPath(), "");

        User existingUser = userRepo.findByEmail(user.getEmail());

        if (existingUser != null) {
            System.out.println("User with the same email already exists");
            session.setAttribute("msg", "User with the same email already exists");
            return "redirect:/register";
        } else {
            User u = userService.saveUser(user, url);

            if (u != null) {
                System.out.println("Registration successful");
                session.setAttribute("msg2", "Register successfully! Please check your email to verify account");
                
                return "redirect:/signin";
            } else {
                System.out.println("Error in server");
                session.setAttribute("msg", "Something went wrong on the server");
                return "redirect:/register";
            }
        }
    }


    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code, Model m) {
        System.out.println("verify");
        boolean f = userService.verifyAccount(code);

        if (f) {
            m.addAttribute("msg", "Sucessfully your account is verified");
        } else {
            m.addAttribute("msg", "Error!!!");
        }
        return "message/message";
    }
}
