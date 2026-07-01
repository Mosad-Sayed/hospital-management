package com.example.hospitalmanagements.service.bedroom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.bedroom.FSBedroom;
import com.example.hospitalmanagements.entity.bedroom.MsBedroom;
import com.example.hospitalmanagements.repository.bedroom.FSBedroomRepository;
import com.example.hospitalmanagements.repository.bedroom.MsBedroomRepository;

@Service
public class FSBedroomService {

    @Autowired
    private FSBedroomRepository fsBedroomRepository;

    // جلب جميع الغرف
    public List<String> getAllRooms() {
        return fsBedroomRepository.findAll()
                .stream()
                .map(FSBedroom::getRoom)
                .distinct()
                .collect(Collectors.toList());
    }

    // جلب الأسرّة بناءً على الغرفة
    public List<String> getBedsByRoom(String room) {
        return fsBedroomRepository.findByRoom(room)
                .stream()
                .map(FSBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    public List<String> getAvailableBedsByRoom(String room) {
        return fsBedroomRepository.findByRoomAndAvailable(room, true)
                .stream()
                .map(FSBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    
}