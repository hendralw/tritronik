package com.procurement.apps.controller;

import com.procurement.apps.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity getAllRooms(){
        try {
            var getAllRooms = roomService.getAllRoom();
            return ResponseEntity.ok().body(getAllRooms);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}
