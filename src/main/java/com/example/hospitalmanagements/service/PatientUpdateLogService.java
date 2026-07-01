package com.example.hospitalmanagements.service;

import com.example.hospitalmanagements.entity.PatientUpdateLog;
import java.util.List;

public interface PatientUpdateLogService {
    
    List<PatientUpdateLog> getAllUpdateLogs();
    
    PatientUpdateLog getUpdateLogById(Long id);
    
    void saveUpdateLog(PatientUpdateLog log);

}
