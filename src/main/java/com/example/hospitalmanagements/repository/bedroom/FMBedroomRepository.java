package com.example.hospitalmanagements.repository.bedroom;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.bedroom.FMBedroom;

public interface FMBedroomRepository extends JpaRepository<FMBedroom, Long> {
    List<FMBedroom> findAll(); // جلب جميع السجلات من الجدول
   // FMBedroom findByRoom(String room); // استعلام للبحث عن السرير بناءً على الغرفة
    List<FMBedroom> findByRoom(String room); // استعلام للبحث عن الأسرّة بناءً على الغرفة
    List<FMBedroom> findByRoomAndAvailable(String room, boolean available);
	FMBedroom findByRoomAndBed(String room, String bed);

}