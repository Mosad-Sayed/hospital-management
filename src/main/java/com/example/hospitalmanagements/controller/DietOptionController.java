package com.example.hospitalmanagements.controller;

import com.example.hospitalmanagements.entity.DietOption;
import com.example.hospitalmanagements.service.DietOptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/diet-options")
public class DietOptionController {

    private final DietOptionService dietOptionService;

    public DietOptionController(DietOptionService dietOptionService) {
        this.dietOptionService = dietOptionService;
    }

    @GetMapping
    public String listDietOptions(Model model) {
        model.addAttribute("specialOptions", dietOptionService.getByType("SPECIAL"));
        model.addAttribute("specialHypoOptions", dietOptionService.getByType("SPECIAL_HYPO"));
        model.addAttribute("specialOtherOptions", dietOptionService.getByType("OTHER_SPECIAL"));
        return "diet-options/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("dietOption", new DietOption());
        return "diet-options/add";
    }

    @PostMapping("/add")
    public String addDietOption(@ModelAttribute DietOption dietOption) {
        dietOptionService.saveDietOption(dietOption);
        return "redirect:/diet-options";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        DietOption dietOption = dietOptionService.getDietOptionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid diet option id: " + id));
        model.addAttribute("dietOption", dietOption);
        return "diet-options/edit";
    }

    @PostMapping("/update/{id}")
    public String updateDietOption(@PathVariable Long id, @ModelAttribute DietOption dietOption) {
        dietOption.setId(id);
        dietOptionService.saveDietOption(dietOption);
        return "redirect:/diet-options";
    }

    @GetMapping("/delete/{id}")
    public String deleteDietOption(@PathVariable Long id) {
        dietOptionService.deleteDietOption(id);
        return "redirect:/diet-options";
    }
    
    @GetMapping("/osf/delete/{id}")
    public String deleteOsfOption(@PathVariable Long id) {
        dietOptionService.deleteDietOption(id);
        return "redirect:/diet-options/osf";
    }

    // Additional endpoints for your specific needs
    @GetMapping("/special")
    public String getSpecialOptions(Model model) {
        model.addAttribute("specialOptions", dietOptionService.getByType("SPECIAL"));
        return "diet-options/special-options";
    }

    @GetMapping("/special-hypo")
    public String getSpecialHypoOptions(Model model) {
        model.addAttribute("specialHypoOptions", dietOptionService.getByType("SPECIAL_HYPO"));
        return "diet-options/special-hypo-options";
    }

    @GetMapping("/other-special")
    public String getOtherSpecialOptions(Model model) {
        model.addAttribute("specialOtherOptions", dietOptionService.getByType("OTHER_SPECIAL"));
        return "diet-options/other-special-options";
    }
    
    @GetMapping("/osf")
    public String listOsfOptions(Model model) {
        model.addAttribute("osfOptions", dietOptionService.getByType("OTHER_OSF"));
        model.addAttribute("generalOptions", dietOptionService.getByType("OTHER_GENERAL"));
        return "diet-options/osf-list";
    }

    @GetMapping("/osf/add")
    public String showAddOsfForm(Model model) {
        DietOption option = new DietOption();
        option.setType("OTHER_OSF"); // تعيين النوع تلقائياً
        model.addAttribute("dietOption", option);
        return "diet-options/osf-add";
    }

    // General Options Management
    @GetMapping("/general")
    public String listGeneralOptions(Model model) {
        model.addAttribute("generalOptions", dietOptionService.getByType("OTHER_GENERAL"));
        return "diet-options/general-list";
    }

    @GetMapping("/general/add")
    public String showAddGeneralForm(Model model) {
        DietOption option = new DietOption();
        option.setType("OTHER_GENERAL"); // تعيين النوع تلقائياً
        model.addAttribute("dietOption", option);
        return "diet-options/general-add";
    }

    // Process forms (يمكن استخدام نفس endpoint للحفظ)
    @PostMapping("/save")
    public String saveDietOption(@ModelAttribute DietOption dietOption) {
        dietOptionService.saveDietOption(dietOption);
        
        if ("OTHER_OSF".equals(dietOption.getType())) {
            return "redirect:/diet-options/osf";
        } else if ("OTHER_GENERAL".equals(dietOption.getType())) {
            return "redirect:/diet-options/general";
        }
        return "redirect:/diet-options";
    }
    
    @GetMapping("/osf/edit/{id}")
    public String showEditOsfForm(@PathVariable Long id, Model model) {
        DietOption dietOption = dietOptionService.getDietOptionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid diet option id: " + id));
        model.addAttribute("dietOption", dietOption);
        return "diet-options/osf-edit";
    }

    // Edit General Option
    @GetMapping("/general/edit/{id}")
    public String showEditGeneralForm(@PathVariable Long id, Model model) {
        DietOption dietOption = dietOptionService.getDietOptionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid diet option id: " + id));
        model.addAttribute("dietOption", dietOption);
        return "diet-options/general-edit";
    }
}