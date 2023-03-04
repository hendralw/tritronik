package com.procurement.apps.repository;

import com.procurement.apps.entity.Reservation;
import com.procurement.apps.entity.ReservationDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query(value = "select * from reservation_tbl where user_id = :user_id", nativeQuery = true)
    Reservation findByUserId(@Param("user_id") int user_id);
}
