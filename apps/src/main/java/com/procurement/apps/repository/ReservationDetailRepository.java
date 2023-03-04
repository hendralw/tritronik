package com.procurement.apps.repository;

import ch.qos.logback.core.model.INamedModel;
import com.procurement.apps.entity.ReservationDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update reservation_detail_tbl set is_check_in = true where id = :reservation_id", nativeQuery = true)
    void checkIn(@Param("reservation_id") int reservation_id);

    @Transactional
    @Modifying
    @Query(value = "update reservation_detail_tbl set is_check_out = true where id = :reservation_id", nativeQuery = true)
    void checkOut(@Param("reservation_id") int reservation_id);

    @Query(value = "select * from reservation_detail_tbl where room_id = :room_id", nativeQuery = true)
    ReservationDetail findByRoomId(@Param("room_id") int room_id);
}
