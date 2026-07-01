package com.example.hospitalmanagements.repository.bedroom;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.bedroom.OBBedroom;

public interface OBBedroomRepository extends JpaRepository<OBBedroom, Long> {
    List<OBBedroom> findAll(); // جلب جميع السجلات من الجدول
   // OBBedroom findByRoom(String room); // استعلام للبحث عن السرير بناءً على الغرفة
    List<OBBedroom> findByRoom(String room); // استعلام للبحث عن الأسرّة بناءً على الغرفة
    List<OBBedroom> findByRoomAndAvailable(String room, boolean available);
	OBBedroom findByRoomAndBed(String room, String bed);
	

}