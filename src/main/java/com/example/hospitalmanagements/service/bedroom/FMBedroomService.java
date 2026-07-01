package com.example.hospitalmanagements.service.bedroom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.bedroom.FMBedroom;
import com.example.hospitalmanagements.repository.bedroom.FMBedroomRepository;

@Service
public class FMBedroomService {

    @Autowired
    private FMBedroomRepository fmBedroomRepository;

    // جلب جميع الغرف
    public List<String> getAllRooms() {
        return fmBedroomRepository.findAll()
                .stream()
                .map(FMBedroom::getRoom)
                .distinct()
                .collect(Collectors.toList());
    }

    // جلب الأسرّة بناءً على الغرفة
    public List<String> getBedsByRoom(String room) {
        return fmBedroomRepository.findByRoom(room)
                .stream()
                .map(FMBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    public List<String> getAvailableBedsByRoom(String room) {
        return fmBedroomRepository.findByRoomAndAvailable(room, true)
                .stream()
                .map(FMBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    
}