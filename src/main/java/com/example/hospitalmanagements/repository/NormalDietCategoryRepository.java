package com.example.hospitalmanagements.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.DietType;
import com.example.hospitalmanagements.entity.NormalDietCategory;

public interface NormalDietCategoryRepository extends JpaRepository<NormalDietCategory, Long> {
    List<NormalDietCategory> findByType(DietType type);
}
