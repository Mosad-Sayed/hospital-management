package com.example.hospitalmanagements.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.PatientAdditionLog;

public interface PatientAdditionLogRepository extends JpaRepository<PatientAdditionLog, Long> {
}
