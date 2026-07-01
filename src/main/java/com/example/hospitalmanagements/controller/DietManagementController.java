package com.example.hospitalmanagements.controller;

import com.example.hospitalmanagements.entity.DietType;
import com.example.hospitalmanagements.entity.NormalDietCategory;
import com.example.hospitalmanagements.entity.NormalDietOption;
import com.example.hospitalmanagements.repository.NormalDietCategoryRepository;
import com.example.hospitalmanagements.repository.NormalDietOptionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/diets")
public class DietManagementController {

    private final NormalDietCategoryRepository categoryRepository;
    private final NormalDietOptionRepository optionRepository;

    public DietManagementController(NormalDietCategoryRepository categoryRepository, 
                                  NormalDietOptionRepository optionRepository) {
        this.categoryRepository = categoryRepository;
        this.optionRepository = optionRepository;
    }

    @GetMapping
    public String listDiets(Model model) {
        List<NormalDietCategory> normalCategories = categoryRepository.findByType(DietType.NORMAL);
        List<NormalDietCategory> hypoCategories = categoryRepository.findByType(DietType.HYPO);
        List<NormalDietCategory> otherCategories = categoryRepository.findByType(DietType.OTHER);
        
        model.addAttribute("normalCategories", normalCategories);
        model.addAttribute("hypoCategories", hypoCategories);
        model.addAttribute("otherCategories", otherCategories);
        
        return "admin/diet-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<NormalDietCategory> normalCategories = categoryRepository.findByType(DietType.NORMAL);
        List<NormalDietCategory> hypoCategories = categoryRepository.findByType(DietType.HYPO);
        List<NormalDietCategory> otherCategories = categoryRepository.findByType(DietType.OTHER);
        
        model.addAttribute("normalCategories", normalCategories);
        model.addAttribute("hypoCategories", hypoCategories);
        model.addAttribute("otherCategories", otherCategories);
        
        return "admin/add-diet";
    }

    @PostMapping("/save")
    public String saveDietOption(@RequestParam Long categoryId,
                                @RequestParam String label,
                                @RequestParam String value,
                                @RequestParam(defaultValue = "false") Boolean active) {
        NormalDietCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        
        NormalDietOption option = new NormalDietOption();
        option.setLabel(label);
        option.setValue(value);
        option.setActive(active);
        option.setCategory(category);
        
        optionRepository.save(option);
        
        return "redirect:/admin/diets";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NormalDietOption option = optionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid option ID"));
        
        model.addAttribute("dietOption", option);
        return "admin/edit-diet";
    }

    @PostMapping("/update")
    public String updateDietOption(@ModelAttribute NormalDietOption dietOption) {
        NormalDietOption existingOption = optionRepository.findById(dietOption.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid option ID"));
        
        existingOption.setLabel(dietOption.getLabel());
        existingOption.setValue(dietOption.getValue());
        existingOption.setActive(dietOption.getActive());
        
        optionRepository.save(existingOption);
        return "redirect:/admin/diets";
    }

    @GetMapping("/delete/{id}")
    public String deleteDietOption(@PathVariable Long id) {
        optionRepository.deleteById(id);
        return "redirect:/admin/diets";
    }
}