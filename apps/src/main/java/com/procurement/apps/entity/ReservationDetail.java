package com.procurement.apps.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation_detail_tbl")
public class ReservationDetail {
    @Id
    private int id;
    private int room_id;
    private int price;
    private Boolean is_check_in;
    private Boolean is_check_out;
}
