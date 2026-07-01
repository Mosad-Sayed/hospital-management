package com.example.hospitalmanagements.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient_update_log")
public class PatientUpdateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = true)
    private String username;

    @Column(name = "department", nullable = true)
    private String department;

    @Column(name = "update_time", nullable = true)
    private LocalDateTime updateTime;

    @Column(name = "patient_file_no", nullable = true)
    private String patientFileNo;

    @Column(name = "changes", columnDefinition = "TEXT", nullable = true)
    private String changes;

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

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getPatientFileNo() {
        return patientFileNo;
    }

    public void setPatientFileNo(String patientFileNo) {
        this.patientFileNo = patientFileNo;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }
}
