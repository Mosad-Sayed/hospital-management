package com.example.hospitalmanagements.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "child_diet_options")
public class ChildDietOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String label;
    private String value;
    private int orderIndex;

    public enum Category {
        CHILD,
        HYPO_CHILD,
        OTHER_CHILD
    }

    // getters & setters
}

