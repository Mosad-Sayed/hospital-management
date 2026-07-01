package com.example.hospitalmanagements.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "diet_options")
public class DietOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String value;

    private String type; // CHILD, SPECIAL, SPECIAL_HYPO, OTHER_SPECIAL ...

    // getters and setters
}
