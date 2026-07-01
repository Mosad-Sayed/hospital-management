package com.example.hospitalmanagements.repository.bedroom;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.bedroom.FSBedroom;

public interface FSBedroomRepository extends JpaRepository<FSBedroom, Long> {
    List<FSBedroom> findAll(); // جلب جميع السجلات من الجدول
   // FSBedroom findByRoom(String room); // استعلام للبحث عن السرير بناءً على الغرفة
    List<FSBedroom> findByRoom(String room); // استعلام للبحث عن الأسرّة بناءً على الغرفة
    List<FSBedroom> findByRoomAndAvailable(String room, boolean available);
	FSBedroom findByRoomAndBed(String room, String bed);

}