package com.example.hospitalmanagements.repository;

import com.example.hospitalmanagements.entity.PatientUpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientUpdateLogRepository extends JpaRepository<PatientUpdateLog, Long> {
}
