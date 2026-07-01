package com.example.hospitalmanagements.repository.bedroom;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.bedroom.MMBedroom;

public interface MMBedroomRepository extends JpaRepository<MMBedroom, Long> {
    List<MMBedroom> findAll(); // جلب جميع السجلات من الجدول
   // MMBedroom findByRoom(String room); // استعلام للبحث عن السرير بناءً على الغرفة
    List<MMBedroom> findByRoom(String room); // استعلام للبحث عن الأسرّة بناءً على الغرفة
    List<MMBedroom> findByRoomAndAvailable(String room, boolean available);
	MMBedroom findByRoomAndBed(String room, String bed);

}