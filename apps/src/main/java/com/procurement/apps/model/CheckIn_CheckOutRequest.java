package com.procurement.apps.model;

import lombok.Data;

@Data
public class CheckIn_CheckOutRequest {
    private int user_id;
    private int room_id;
}
