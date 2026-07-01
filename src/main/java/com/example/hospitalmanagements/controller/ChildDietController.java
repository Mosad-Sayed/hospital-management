package com.example.hospitalmanagements.controller;

import com.example.hospitalmanagements.entity.ChildDietOption;
import com.example.hospitalmanagements.repository.ChildDietOptionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/child-diet")
public class ChildDietController {

    private final ChildDietOptionRepository repository;

    public ChildDietController(ChildDietOptionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getChildDiet(Model model) {
        model.addAttribute("childDietOptions", repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.CHILD));
        model.addAttribute("hypoChildDietOptions", repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.HYPO_CHILD));
        model.addAttribute("otherChildDietOptions", repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.OTHER_CHILD));
        return "child_diet_table";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("childDietOption", new ChildDietOption());
        return "add_child_diet";
    }

    @PostMapping("/save")
    public String saveOption(@ModelAttribute ChildDietOption childDietOption) {
        repository.save(childDietOption);
        return "redirect:/child-diet";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ChildDietOption option = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid option Id:" + id));
        model.addAttribute("childDietOption", option);
        return "edit_child_diet";
    }

    @PostMapping("/update")
    public String updateOption(@ModelAttribute ChildDietOption childDietOption) {
        repository.save(childDietOption);
        return "redirect:/child-diet";
    }

    @GetMapping("/delete/{id}")
    public String deleteOption(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/child-diet";
    }
}