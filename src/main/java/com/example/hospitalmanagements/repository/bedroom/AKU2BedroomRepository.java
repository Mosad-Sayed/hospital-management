package com.example.hospitalmanagements.repository.bedroom;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.bedroom.AKU2Bedroom;

public interface AKU2BedroomRepository extends JpaRepository<AKU2Bedroom, Long> {
    List<AKU2Bedroom> findAll(); // جلب جميع السجلات من الجدول
   // AKU2Bedroom findByRoom(String room); // استعلام للبحث عن السرير بناءً على الغرفة
    List<AKU2Bedroom> findByRoom(String room); // استعلام للبحث عن الأسرّة بناءً على الغرفة
    List<AKU2Bedroom> findByRoomAndAvailable(String room, boolean available);
	AKU2Bedroom findByRoomAndBed(String room, String bed);

}