package com.example.hospitalmanagements.repository.bedroom;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospitalmanagements.entity.bedroom.NURSERYBedroom;

public interface NURSERYBedroomRepository extends JpaRepository<NURSERYBedroom, Long> {
    List<NURSERYBedroom> findAll(); // جلب جميع السجلات من الجدول
   // NURSERYBedroom findByRoom(String room); // استعلام للبحث عن السرير بناءً على الغرفة
    List<NURSERYBedroom> findByRoom(String room); // استعلام للبحث عن الأسرّة بناءً على الغرفة
    List<NURSERYBedroom> findByRoomAndAvailable(String room, boolean available);
	NURSERYBedroom findByRoomAndBed(String room, String bed);

}