package com.procurement.apps.controller;

import com.google.gson.Gson;
import com.procurement.apps.model.CheckIn_CheckOutRequest;
import com.procurement.apps.model.LoginRequest;
import com.procurement.apps.model.RegisterRequest;
import com.procurement.apps.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private KafkaTemplate<String,String> kafkaTemplate;

    public UserController(UserService userService, KafkaTemplate<String, String> kafkaTemplate) {
        this.userService = userService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/users/login")
    public ResponseEntity authenticate(@RequestBody LoginRequest request){
        try {
            var getUserByUsername = userService.findByUsername(request.getUsername());
            if (getUserByUsername == null) return ResponseEntity.badRequest().body("Invalid username");
            if (!getUserByUsername.getPassword().equals(request.getPassword())) return ResponseEntity.badRequest().body("Invalid password");
            return ResponseEntity.ok().body("Success login");
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }

    @PostMapping("/users/register")
    public ResponseEntity createUser(@RequestBody RegisterRequest request){
        try {
            return userService.createUser(request);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }

    @PostMapping("/users/check_in")
    public ResponseEntity checkInRoom(@RequestBody CheckIn_CheckOutRequest request){
        try {
            return userService.checkInRoom(request);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }

    @PostMapping("/users/check_out")
    public ResponseEntity checkOutRoom(@RequestBody CheckIn_CheckOutRequest request){
        try {
            return userService.checkOutRoom(request);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }

    @GetMapping("/users")
    public ResponseEntity getAllUser(){
        var getAllUser = userService.findAll();
        kafkaTemplate.send("getUser", new Gson().toJson(getAllUser));
        return ResponseEntity.ok().body("Success get data user, check your console");
    }
}
