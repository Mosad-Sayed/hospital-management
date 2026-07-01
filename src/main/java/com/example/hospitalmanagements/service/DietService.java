package com.example.hospitalmanagements.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.DietType;
import com.example.hospitalmanagements.entity.NormalDietCategory;
import com.example.hospitalmanagements.repository.NormalDietCategoryRepository;

@Service
public class DietService {

    private final NormalDietCategoryRepository categoryRepo;

    public DietService(NormalDietCategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<NormalDietCategory> getAllCategories() {
        return categoryRepo.findAll();
    }

    public List<NormalDietCategory> getCategoriesByType(DietType type) {
        return categoryRepo.findByType(type);
    }
}
