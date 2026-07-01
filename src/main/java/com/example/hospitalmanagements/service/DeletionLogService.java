package com.example.hospitalmanagements.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.repository.DeletionLogRepository;
@Service
public class DeletionLogService {

    @Autowired
    private DeletionLogRepository deletionLogRepository;

    public void deleteLogById(Long id) {
        deletionLogRepository.deleteById(id);
    }
}
