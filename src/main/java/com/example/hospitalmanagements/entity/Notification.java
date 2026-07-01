package com.example.hospitalmanagements.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mealType;
    private String date;
    private String day;
    private String status;
    private Timestamp time;
    private LocalDateTime Timestamp;
    private String departmentName;
    private String nurseName;

    private String nurseFileNumber;
    // constructors, getters, and setters
    // ...
}
