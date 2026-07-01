package com.example.hospitalmanagements.service.bedroom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.bedroom.MMBedroom;
import com.example.hospitalmanagements.entity.bedroom.MsBedroom;
import com.example.hospitalmanagements.repository.bedroom.MMBedroomRepository;
import com.example.hospitalmanagements.repository.bedroom.MsBedroomRepository;

@Service
public class MMBedroomService {

    @Autowired
    private MMBedroomRepository mmBedroomRepository;

    // جلب جميع الغرف
    public List<String> getAllRooms() {
        return mmBedroomRepository.findAll()
                .stream()
                .map(MMBedroom::getRoom)
                .distinct()
                .collect(Collectors.toList());
    }

    // جلب الأسرّة بناءً على الغرفة
    public List<String> getBedsByRoom(String room) {
        return mmBedroomRepository.findByRoom(room)
                .stream()
                .map(MMBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    public List<String> getAvailableBedsByRoom(String room) {
        return mmBedroomRepository.findByRoomAndAvailable(room, true)
                .stream()
                .map(MMBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    
}