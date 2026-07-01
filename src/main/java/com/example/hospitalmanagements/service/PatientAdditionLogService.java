package com.example.hospitalmanagements.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.PatientAdditionLog;
import com.example.hospitalmanagements.repository.PatientAdditionLogRepository;

@Service
public class PatientAdditionLogService {

    @Autowired
    private PatientAdditionLogRepository logRepository;

    public List<PatientAdditionLog> getAllLogs() {
        return logRepository.findAll();
    }

    public void deleteLogById(Long id) {
        logRepository.deleteById(id);
    }
}
