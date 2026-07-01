package com.example.hospitalmanagements.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class NormalDietOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String value;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private NormalDietCategory category;

    // Getters and Setters
}
