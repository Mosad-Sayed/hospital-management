package com.example.hospitalmanagements.service.bedroom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.bedroom.MsBedroom;
import com.example.hospitalmanagements.entity.bedroom.OBBedroom;
import com.example.hospitalmanagements.repository.bedroom.MsBedroomRepository;
import com.example.hospitalmanagements.repository.bedroom.OBBedroomRepository;

@Service
public class OBBedroomService {

    @Autowired
    private OBBedroomRepository obBedroomRepository;

    // جلب جميع الغرف
    public List<String> getAllRooms() {
        List<String> rooms = obBedroomRepository.findAll()
                .stream()
                .map(OBBedroom::getRoom)
                .distinct()
                .collect(Collectors.toList());

        // هنا اللوج اللي هيطبع القيم في الكونسول:
        System.out.println("[DEBUG] Rooms fetched from DB for OB: " + rooms);

        return rooms;
    }

    // جلب الأسرّة بناءً على الغرفة
    public List<String> getBedsByRoom(String room) {
        return obBedroomRepository.findByRoom(room)
                .stream()
                .map(OBBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    public List<String> getAvailableBedsByRoom(String room) {
        return obBedroomRepository.findByRoomAndAvailable(room, true)
                .stream()
                .map(OBBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    
}