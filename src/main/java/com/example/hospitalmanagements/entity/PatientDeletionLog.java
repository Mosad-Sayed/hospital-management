package com.example.hospitalmanagements.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_deletion_log")
public class PatientDeletionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = true)
    private String username;

    @Column(name = "department", nullable = true)
    private String department;

    @Column(name = "deletion_time", nullable = true)
    private LocalDateTime deletionTime;

    @Column(name = "patient_file_no", nullable = true)
    private String patientFileNo;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getDeletionTime() {
        return deletionTime;
    }

    public void setDeletionTime(LocalDateTime deletionTime) {
        this.deletionTime = deletionTime;
    }

    public String getPatientFileNo() {
        return patientFileNo;
    }

    public void setPatientFileNo(String patientFileNo) {
        this.patientFileNo = patientFileNo;
    }
}
