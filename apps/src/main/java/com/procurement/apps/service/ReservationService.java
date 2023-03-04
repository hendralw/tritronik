package com.procurement.apps.service;

import com.procurement.apps.entity.Reservation;
import com.procurement.apps.entity.ReservationDetail;
import com.procurement.apps.entity.Room;
import com.procurement.apps.model.ReservationDetailResponse;
import com.procurement.apps.model.ReservationRequest;
import com.procurement.apps.model.ReservationResponse;
import com.procurement.apps.repository.ReservationDetailRepository;
import com.procurement.apps.repository.ReservationRepository;
import com.procurement.apps.repository.RoomRepository;
import com.procurement.apps.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationDetailRepository reservationDetailRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public ResponseEntity createReservation(ReservationRequest request){
        long sizeReservation = reservationRepository.count();
        int newIdReservation = (int)sizeReservation + 1;
        var checkUser = userRepository.findById(request.getUser_id());
        if (checkUser == null) return ResponseEntity.badRequest().body("User with id = " + request.getUser_id() + " is not found");
        List<Optional<Room>> listRoom = new ArrayList<>();
        var subtotalRoom = 0;
        for (var room: request.getReservations()) {
            var checkRoom = roomRepository.findById(room.getRoom_id());

            if (checkRoom == null) return ResponseEntity.badRequest().body("Room with id = " + room.getRoom_id() + " is not found");
            if (checkRoom.get().getIs_booked().equals(true)) return ResponseEntity.badRequest().body("Room with id = " + room.getRoom_id() + " is booked");
            listRoom.add(checkRoom);
            subtotalRoom += checkRoom.get().getPrice();
        }

        var newReservation = new Reservation(newIdReservation, request.getUser_id(), subtotalRoom);
        reservationRepository.save(newReservation);
        var dataReservation = new ReservationResponse();
        List<ReservationDetailResponse> listDetail = new ArrayList<ReservationDetailResponse>();
        for(var room: listRoom){
            long sizeReservationDetail = reservationDetailRepository.count();
            int newIdDetail = (int)sizeReservationDetail + 1;
            var newReservationDetail = new ReservationDetail(newIdDetail, room.get().getId(), room.get().getPrice(), false, false);
            reservationDetailRepository.save(newReservationDetail);
            roomRepository.bookingRoom(room.get().getId());
            var dataDetail = new ReservationDetailResponse();
            dataDetail.setRoom_id(room.get().getId());
            dataDetail.setPrice(room.get().getPrice());
            listDetail.add(dataDetail);
        }
        dataReservation.setId(newIdReservation);
        dataReservation.setUser_id(request.getUser_id());
        dataReservation.setSubtotal(subtotalRoom);
        dataReservation.setReservations(listDetail);
        return ResponseEntity.ok().body(dataReservation);
    }
}
