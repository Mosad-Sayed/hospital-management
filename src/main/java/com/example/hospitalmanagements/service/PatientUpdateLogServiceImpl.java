package com.example.hospitalmanagements.service;

import com.example.hospitalmanagements.entity.PatientUpdateLog;
import com.example.hospitalmanagements.repository.PatientUpdateLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientUpdateLogServiceImpl implements PatientUpdateLogService {

    @Autowired
    private PatientUpdateLogRepository patientUpdateLogRepository;

    @Override
    public List<PatientUpdateLog> getAllUpdateLogs() {
        return patientUpdateLogRepository.findAll();
    }

    @Override
    public PatientUpdateLog getUpdateLogById(Long id) {
        return patientUpdateLogRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUpdateLog(PatientUpdateLog log) {
        patientUpdateLogRepository.save(log);
    }

	
}
