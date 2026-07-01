package com.example.hospitalmanagements.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalmanagements.entity.PatientDeletionLog;

@Repository
public interface DeletionLogRepository extends JpaRepository<PatientDeletionLog, Long> {
}
