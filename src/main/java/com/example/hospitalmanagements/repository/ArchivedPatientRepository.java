package com.example.hospitalmanagements.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalmanagements.entity.Patient;

@Repository
public interface ArchivedPatientRepository extends JpaRepository<Patient, Long> {
}
