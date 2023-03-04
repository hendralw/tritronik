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
@Table(name = "room_tbl")
public class Room {
    @Id
    private int id;
    private String kind;
    private int price;
    private Boolean is_booked;
}
