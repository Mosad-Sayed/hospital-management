package com.example.hospitalmanagements.repository.bedroom;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.bedroom.AKU1Bedroom;

public interface AKU1BedroomRepository extends JpaRepository<AKU1Bedroom, Long> {
    List<AKU1Bedroom> findAll(); // جلب جميع السجلات من الجدول
   // AKU1Bedroom findByRoom(String room); // استعلام للبحث عن السرير بناءً على الغرفة
    List<AKU1Bedroom> findByRoom(String room); // استعلام للبحث عن الأسرّة بناءً على الغرفة
    List<AKU1Bedroom> findByRoomAndAvailable(String room, boolean available);
	AKU1Bedroom findByRoomAndBed(String room, String bed);

}