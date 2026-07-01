package com.example.hospitalmanagements.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.DietOption;

public interface DietOptionRepository extends JpaRepository<DietOption, Long> {
	List<DietOption> findByType(String type);
    Optional<DietOption> findById(Long id);
    }
