package com.example.hospitalmanagements.service.bedroom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.bedroom.MsBedroom;
import com.example.hospitalmanagements.entity.bedroom.PEDIABedroom;
import com.example.hospitalmanagements.repository.bedroom.MsBedroomRepository;
import com.example.hospitalmanagements.repository.bedroom.PEDIABedroomRepository;

@Service
public class PEDIABedroomService {

    @Autowired
    private PEDIABedroomRepository pediaBedroomRepository;

    // جلب جميع الغرف
    public List<String> getAllRooms() {
        return pediaBedroomRepository.findAll()
                .stream()
                .map(PEDIABedroom::getRoom)
                .distinct()
                .collect(Collectors.toList());
    }

    // جلب الأسرّة بناءً على الغرفة
    public List<String> getBedsByRoom(String room) {
        return pediaBedroomRepository.findByRoom(room)
                .stream()
                .map(PEDIABedroom::getBed)
                .collect(Collectors.toList());
    }
    
    public List<String> getAvailableBedsByRoom(String room) {
        return pediaBedroomRepository.findByRoomAndAvailable(room, true)
                .stream()
                .map(PEDIABedroom::getBed)
                .collect(Collectors.toList());
    }
    
    
}