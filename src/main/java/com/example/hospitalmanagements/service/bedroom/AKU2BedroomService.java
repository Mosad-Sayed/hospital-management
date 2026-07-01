package com.example.hospitalmanagements.service.bedroom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.bedroom.AKU2Bedroom;
import com.example.hospitalmanagements.repository.bedroom.AKU2BedroomRepository;

@Service
public class AKU2BedroomService {

    @Autowired
    private AKU2BedroomRepository aku2BedroomRepository;

    // جلب جميع الغرف
    public List<String> getAllRooms() {
        return aku2BedroomRepository.findAll()
                .stream()
                .map(AKU2Bedroom::getRoom)
                .distinct()
                .collect(Collectors.toList());
    }

    // جلب الأسرّة بناءً على الغرفة
    public List<String> getBedsByRoom(String room) {
        return aku2BedroomRepository.findByRoom(room)
                .stream()
                .map(AKU2Bedroom::getBed)
                .collect(Collectors.toList());
    }
    
    public List<String> getAvailableBedsByRoom(String room) {
        return aku2BedroomRepository.findByRoomAndAvailable(room, true)
                .stream()
                .map(AKU2Bedroom::getBed)
                .collect(Collectors.toList());
    }
    
    
}