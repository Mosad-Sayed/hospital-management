package com.example.hospitalmanagements.repository.bedroom;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.bedroom.MsBedroom;

public interface MsBedroomRepository extends JpaRepository<MsBedroom, Long> {
    List<MsBedroom> findAll(); // جلب جميع السجلات من الجدول
   // MsBedroom findByRoom(String room); // استعلام للبحث عن السرير بناءً على الغرفة
    List<MsBedroom> findByRoom(String room); // استعلام للبحث عن الأسرّة بناءً على الغرفة
    List<MsBedroom> findByRoomAndAvailable(String room, boolean available);
	MsBedroom findByRoomAndBed(String room, String bed);

}