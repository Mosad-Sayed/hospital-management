package com.example.hospitalmanagements.repository.bedroom;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.bedroom.PEDIABedroom;

public interface PEDIABedroomRepository extends JpaRepository<PEDIABedroom, Long> {
    List<PEDIABedroom> findAll(); // جلب جميع السجلات من الجدول
   // PEDIABedroom findByRoom(String room); // استعلام للبحث عن السرير بناءً على الغرفة
    List<PEDIABedroom> findByRoom(String room); // استعلام للبحث عن الأسرّة بناءً على الغرفة
    List<PEDIABedroom> findByRoomAndAvailable(String room, boolean available);
	PEDIABedroom findByRoomAndBed(String room, String bed);

}