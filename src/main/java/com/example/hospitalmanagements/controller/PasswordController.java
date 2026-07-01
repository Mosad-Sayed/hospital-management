package com.example.hospitalmanagements.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hospitalmanagements.entity.User;
import com.example.hospitalmanagements.service.UserService;

@Controller
public class PasswordController {
	
	@Autowired
	UserService userService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password"; // Name of the reset password page
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String username,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model) {

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("message", "Passwords do not match");
            return "forgot-password";
        }

        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(newPassword); // Encrypt the password if needed
            userService.saveUser(user); // Save the updated user
            model.addAttribute("successMessage", "Password reset successfully!");
            return "login"; // Redirect to login page
        } else {
            model.addAttribute("message", "Username not found");
            return "forgot-password";
        }
    }
}
