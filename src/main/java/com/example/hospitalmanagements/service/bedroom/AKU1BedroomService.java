package com.example.hospitalmanagements.service.bedroom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.bedroom.AKU1Bedroom;
import com.example.hospitalmanagements.repository.bedroom.AKU1BedroomRepository;

@Service
public class AKU1BedroomService {

    @Autowired
    private AKU1BedroomRepository aku1BedroomRepository  ;

    // جلب جميع الغرف
    public List<String> getAllRooms() {
        return aku1BedroomRepository.findAll()
                .stream()
                .map(AKU1Bedroom::getRoom)
                .distinct()
                .collect(Collectors.toList());
    }

    // جلب الأسرّة بناءً على الغرفة
    public List<String> getBedsByRoom(String room) {
        return aku1BedroomRepository.findByRoom(room)
                .stream()
                .map(AKU1Bedroom::getBed)
                .collect(Collectors.toList());
    }
    
    public List<String> getAvailableBedsByRoom(String room) {
        return aku1BedroomRepository.findByRoomAndAvailable(room, true)
                .stream()
                .map(AKU1Bedroom::getBed)
                .collect(Collectors.toList());
    }
    
    
}