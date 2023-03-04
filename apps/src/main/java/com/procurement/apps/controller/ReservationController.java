package com.procurement.apps.controller;

import com.procurement.apps.model.ReservationRequest;
import com.procurement.apps.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservations")
    public ResponseEntity createReservation(@RequestBody ReservationRequest request){
        try {
            return reservationService.createReservation(request);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}
