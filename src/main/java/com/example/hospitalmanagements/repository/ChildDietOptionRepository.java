package com.example.hospitalmanagements.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalmanagements.entity.ChildDietOption;

@Repository
public interface ChildDietOptionRepository extends JpaRepository<ChildDietOption, Long> {
    List<ChildDietOption> findByCategoryOrderByOrderIndex(ChildDietOption.Category category);
}

