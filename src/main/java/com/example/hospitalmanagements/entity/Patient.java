package com.example.hospitalmanagements.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileNo;
    
    private String nameOfPatient;
    private String age;
    private String room;
    private String bed;
    private String patternFeeds;
    private String diagnosis;
    private String dietOrder;
    private String subDietOrder;
    private String notes;
    private String admissionDate;
    private String companionName;
    private String registeredByDepartment; // حقل لتخزين اسم القسم
    private Boolean archived;
    private String deletedFromDepartment; // خاصية جديدة لتخزين القسم الذي تم الحذف منه
    private LocalDateTime archivedAt; // تأكد من أن هذا الحقل موجود

    // Getters and setters
    // باقي الكود
}
