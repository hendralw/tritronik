package com.procurement.apps.service;

import com.google.gson.Gson;
import com.procurement.apps.entity.User;
import com.procurement.apps.model.CheckIn_CheckOutRequest;
import com.procurement.apps.model.RegisterRequest;
import com.procurement.apps.model.UserResponse;
import com.procurement.apps.repository.ReservationDetailRepository;
import com.procurement.apps.repository.ReservationRepository;
import com.procurement.apps.repository.RoomRepository;
import com.procurement.apps.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
import org.apache.catalina.mapper.Mapper;
import org.apache.coyote.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationDetailRepository reservationDetailRepository;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public ResponseEntity createUser(RegisterRequest request){
        long size = userRepository.count();
        Integer newId = (int)size + 1;
        var getByUsername = userRepository.findByUsername(request.getUsername());
        if (getByUsername != null) return ResponseEntity.badRequest().body("Username already registered");

        var newUser = new User(newId, request.getUsername(), request.getPassword());
        userRepository.save(newUser);
        return ResponseEntity.ok().body("Success register");
    }

    public ResponseEntity checkInRoom(CheckIn_CheckOutRequest request){
        var checkRoom = roomRepository.findById(request.getRoom_id());
        if (checkRoom.isEmpty()) return ResponseEntity.badRequest().body("Room with id = " + request.getRoom_id() + " is not found");
        var checkReservation = reservationRepository.findByUserId(request.getUser_id());
        if (checkReservation == null) return ResponseEntity.badRequest().body("Room with id = " + request.getRoom_id() + " is not found in your reservation");
        var checkReservationDetail = reservationDetailRepository.findByRoomId(request.getRoom_id());
        if (checkReservationDetail == null) return ResponseEntity.badRequest().body("Room with id = " + request.getRoom_id() + " is not found in your reservation");
        if (checkReservationDetail.getIs_check_out().equals(true)) return ResponseEntity.badRequest().body("Room with id = " + request.getRoom_id() + " is already checked out");
        if (checkReservationDetail.getIs_check_in().equals(true)) return ResponseEntity.badRequest().body("Room with id = " + request.getRoom_id() + " is already checked in");

        reservationDetailRepository.checkIn(request.getRoom_id());
        return ResponseEntity.ok().body("Success check in room for room id = " + request.getRoom_id());
    }

    public ResponseEntity checkOutRoom(CheckIn_CheckOutRequest request){
        var checkRoom = roomRepository.findById(request.getRoom_id());
        if (checkRoom.isEmpty()) return ResponseEntity.badRequest().body("Room with id = " + request.getRoom_id() + " is not found");
        var checkReservation = reservationRepository.findByUserId(request.getUser_id());
        if (checkReservation == null) return ResponseEntity.badRequest().body("Room with id = " + request.getRoom_id() + " is not found in your reservation");
        var checkReservationDetail = reservationDetailRepository.findByRoomId(request.getRoom_id());
        if (checkReservationDetail == null) return ResponseEntity.badRequest().body("Room with id = " + request.getRoom_id() + " is not found in your reservation");
        if (checkReservationDetail.getIs_check_out().equals(true)) return ResponseEntity.badRequest().body("Room with id = " + request.getRoom_id() + " is already checked out");
        if (checkReservationDetail.getIs_check_in().equals(false)) return ResponseEntity.badRequest().body("Room with id = " + request.getRoom_id() + " is not check in yet");

        reservationDetailRepository.checkOut(request.getRoom_id());
        return ResponseEntity.ok().body("Success check out room for room id = " + request.getRoom_id());
    }

    @KafkaListener(topics = "getUser")
    public void subscribeGetUser(String message){
        System.out.println(message);
    }
}
