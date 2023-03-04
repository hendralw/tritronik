package com.procurement.apps.repository;

import com.procurement.apps.entity.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update room_tbl set is_booked = true where id = :room_id", nativeQuery = true)
    void bookingRoom(@Param("room_id") int room_id);
}
