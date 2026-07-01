package com.example.hospitalmanagements.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.DietCategory;

public interface DietCategoryRepository extends JpaRepository<DietCategory, Long> {
    List<DietCategory> findByCategoryType(String categoryType);
}