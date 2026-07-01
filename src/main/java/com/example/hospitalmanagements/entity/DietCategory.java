package com.example.hospitalmanagements.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "diet_categories")
@Setter
@Getter
public class DietCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String categoryType; // "normal", "hypo", "other"
    private String dietName;
    private String description;
    private String pdfLink;
    
    // Getters and Setters
}