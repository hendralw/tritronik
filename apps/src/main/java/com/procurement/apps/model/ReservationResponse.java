package com.procurement.apps.model;

import lombok.Data;

import java.util.List;

@Data
public class ReservationResponse {
    private int id;
    private int user_id;
    private int subtotal;
    private List<ReservationDetailResponse> reservations;
}
