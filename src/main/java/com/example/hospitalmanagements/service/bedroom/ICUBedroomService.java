package com.example.hospitalmanagements.service.bedroom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.bedroom.ICUBedroom;
import com.example.hospitalmanagements.entity.bedroom.MsBedroom;
import com.example.hospitalmanagements.repository.bedroom.ICUBedroomRepository;
import com.example.hospitalmanagements.repository.bedroom.MsBedroomRepository;

@Service
public class ICUBedroomService {

    @Autowired
    private ICUBedroomRepository icuBedroomRepository;

    // جلب جميع الغرف
    public List<String> getAllRooms() {
        return icuBedroomRepository.findAll()
                .stream()
                .map(ICUBedroom::getRoom)
                .distinct()
                .collect(Collectors.toList());
    }

    // جلب الأسرّة بناءً على الغرفة
    public List<String> getBedsByRoom(String room) {
        return icuBedroomRepository.findByRoom(room)
                .stream()
                .map(ICUBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    public List<String> getAvailableBedsByRoom(String room) {
        return icuBedroomRepository.findByRoomAndAvailable(room, true)
                .stream()
                .map(ICUBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    
}