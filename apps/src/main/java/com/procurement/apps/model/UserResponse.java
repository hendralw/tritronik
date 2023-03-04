package com.procurement.apps.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String username;
    private String password;
}
