package com.example.project.Controllers;

import com.example.project.Models.User;
import com.example.project.Repository.UserRepository;
import com.example.project.Services.UserService;
import com.example.project.config.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/profile")
    public String viewProfile(@AuthenticationPrincipal CustomUser loggedUser, Model model) {
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        model.addAttribute("user", user);
        return "User/profile";
    }

    @PostMapping("/profile/update")
    public String saveProfile(User user, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CustomUser loggedUser) {
        User u = userRepo.findByEmail(loggedUser.getUsername());
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setAddress(user.getAddress());
        u.setPhone(user.getPhone());
        userService.updateUser(u);
        loggedUser.setUserName(user.getName());
        redirectAttributes.addFlashAttribute("message", "Your profile have been updated");
        return "redirect:/users/profile";
    }

    @GetMapping("/change-password")
    public String loadChangePassword(@AuthenticationPrincipal CustomUser loggedUser, Model model) {
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        model.addAttribute("user", user);
        return "User/changepassword";
    }

    @PostMapping("/change-password/update")
    public String changePassword(@AuthenticationPrincipal CustomUser loggedUser, @RequestParam("currentPass") String currentPass,
                                 @RequestParam("newPass") String newPass, @RequestParam("cNewPass") String cNewPass, RedirectAttributes redirectAttributes) {
        String email = loggedUser.getUsername();
        User user = userRepo.findByEmail(email);
        if (userService.oldPasswordIsValid(user, currentPass)) {
            if (cNewPass.equals(newPass)) {
                userService.changePassword(user, newPass);
                redirectAttributes.addFlashAttribute("successMsg","Password changed successfully");
                return "redirect:/users/change-password";
            }
            else {
                redirectAttributes.addFlashAttribute("message","Password confirmation doesn't match the password");

            }
        }
        else {
            redirectAttributes.addFlashAttribute("message","Old password isn't valid");
        }
        return "redirect:/users/change-password";
    }
}
