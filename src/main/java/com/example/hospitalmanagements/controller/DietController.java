package com.example.hospitalmanagements.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.hospitalmanagements.entity.DietType;
import com.example.hospitalmanagements.service.DietService;

@Controller
public class DietController {

    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping("/normal-diet")
    public String showNormalDiet(Model model) {
        model.addAttribute("normalCategories", dietService.getCategoriesByType(DietType.NORMAL));
        model.addAttribute("hypoCategories", dietService.getCategoriesByType(DietType.HYPO));
        model.addAttribute("otherCategories", dietService.getCategoriesByType(DietType.OTHER));
        return "normal_diet_table";
    }
}
