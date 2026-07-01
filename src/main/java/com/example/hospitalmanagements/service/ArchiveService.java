package com.example.hospitalmanagements.service;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.repository.PatientRepository;

@Service
public class ArchiveService {

    private final PatientRepository patientRepository;

    public ArchiveService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public boolean existsByFileNo(String fileNo) {
        return patientRepository.existsByFileNoAndArchivedTrue(fileNo);
    }
}
