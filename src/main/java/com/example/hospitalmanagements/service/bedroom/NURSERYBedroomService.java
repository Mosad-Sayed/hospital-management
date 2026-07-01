package com.example.hospitalmanagements.service.bedroom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.bedroom.MsBedroom;
import com.example.hospitalmanagements.entity.bedroom.NURSERYBedroom;
import com.example.hospitalmanagements.repository.bedroom.MsBedroomRepository;
import com.example.hospitalmanagements.repository.bedroom.NURSERYBedroomRepository;

@Service
public class NURSERYBedroomService {

    @Autowired
    private NURSERYBedroomRepository nurseryBedroomRepository;

    // جلب جميع الغرف
    public List<String> getAllRooms() {
        return nurseryBedroomRepository.findAll()
                .stream()
                .map(NURSERYBedroom::getRoom)
                .distinct()
                .collect(Collectors.toList());
    }

    // جلب الأسرّة بناءً على الغرفة
    public List<String> getBedsByRoom(String room) {
        return nurseryBedroomRepository.findByRoom(room)
                .stream()
                .map(NURSERYBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    public List<String> getAvailableBedsByRoom(String room) {
        return nurseryBedroomRepository.findByRoomAndAvailable(room, true)
                .stream()
                .map(NURSERYBedroom::getBed)
                .collect(Collectors.toList());
    }
    
    
}