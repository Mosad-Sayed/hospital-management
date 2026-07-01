package com.example.hospitalmanagements.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class NormalDietCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String pdfLink;
    private String code;

    @Enumerated(EnumType.STRING)
    private DietType type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<NormalDietOption> options;

    // Getters and Setters
}
