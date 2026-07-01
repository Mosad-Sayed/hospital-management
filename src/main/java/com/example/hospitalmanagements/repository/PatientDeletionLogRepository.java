package com.example.hospitalmanagements.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalmanagements.entity.PatientDeletionLog;

@Repository
public interface PatientDeletionLogRepository extends JpaRepository<PatientDeletionLog, Long> {

	List<PatientDeletionLog> findAllByOrderByDeletionTimeDesc();
}
