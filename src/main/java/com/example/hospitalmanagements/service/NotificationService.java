package com.example.hospitalmanagements.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.Notification;
import com.example.hospitalmanagements.repository.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

	public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
		
	}
	 public boolean updateNotificationStatus(Long id, String status) {
	        Optional<Notification> notification = notificationRepository.findById(id);
	        if (notification.isPresent()) {
	            Notification notif = notification.get();
	            notif.setStatus(status);
	            notificationRepository.save(notif);
	            return true;
	        }
	        return false;
	    }
	 
	 public List<Notification> getAllNotifications() {
	        return notificationRepository.findAll();
	    }
	 public List<Notification> findByDepartmentName(String departmentName) {
	        return notificationRepository.findByDepartmentName(departmentName);
	    }
	 
	 @Transactional
	    public void updateNotificationStatus(String nurseFileNumber) {
	        List<Notification> notifications = notificationRepository.findByNurseFileNumberAndStatus(nurseFileNumber, "read");
	        for (Notification notification : notifications) {
	            notification.setStatus(null); // إزالة قيمة read
	        }
	        notificationRepository.saveAll(notifications);
	    }
	 
	 
	 
    
}
