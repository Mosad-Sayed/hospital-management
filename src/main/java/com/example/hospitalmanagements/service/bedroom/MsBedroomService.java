package com.example.hospitalmanagements.service.bedroom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.bedroom.MsBedroom;
import com.example.hospitalmanagements.repository.bedroom.MsBedroomRepository;

@Service
public class MsBedroomService {

    @Autowired
    private MsBedroomRepository msBedroomRepository;

    // جلب جميع الغرف
    public List<String> getAllRooms() {
        return msBedroomRepository.findAll()
                .stream()
                .map(MsBedroom::getRoom)
                .distinct()
                .collect(Collectors.toList());
    }

    // جلب الأسرّة بناءً على الغرفة
    public List<String> getBedsByRoom(String room) {
        return msBedroomRepository.findByRoom(room)
                .stream()
                .map(MsBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    public List<String> getAvailableBedsByRoom(String room) {
        return msBedroomRepository.findByRoomAndAvailable(room, true)
                .stream()
                .map(MsBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    
}