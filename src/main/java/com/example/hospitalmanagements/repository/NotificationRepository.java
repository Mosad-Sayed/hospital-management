package com.example.hospitalmanagements.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hospitalmanagements.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
    List<Notification> findByDepartmentName(String departmentName);
    List<Notification> findByNurseFileNumberAndStatus(String nurseFileNumber, String status);
    

}
