package com.example.hospitalmanagements.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalmanagements.entity.ChildDietOption;
import com.example.hospitalmanagements.repository.ChildDietOptionRepository;

@RestController
@RequestMapping("/api/diet-options")
public class ChildDietOptionController {

    @Autowired
    private ChildDietOptionRepository repository;

   
    @GetMapping("/child-diet")
    public String getChildDiet(Model model) {
        model.addAttribute("childDietOptions", repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.CHILD));
        model.addAttribute("hypoChildDietOptions", repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.HYPO_CHILD));
        model.addAttribute("otherChildDietOptions", repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.OTHER_CHILD));
        return "child_diet_table";
    }
}
