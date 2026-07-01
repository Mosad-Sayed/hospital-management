package com.example.hospitalmanagements.repository.bedroom;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.bedroom.ICUBedroom;

public interface ICUBedroomRepository extends JpaRepository<ICUBedroom, Long> {
    List<ICUBedroom> findAll(); // جلب جميع السجلات من الجدول
   // ICUBedroom findByRoom(String room); // استعلام للبحث عن السرير بناءً على الغرفة
    List<ICUBedroom> findByRoom(String room); // استعلام للبحث عن الأسرّة بناءً على الغرفة
    List<ICUBedroom> findByRoomAndAvailable(String room, boolean available);
	ICUBedroom findByRoomAndBed(String room, String bed);

}