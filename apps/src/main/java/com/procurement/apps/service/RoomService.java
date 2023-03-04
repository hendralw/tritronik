package com.procurement.apps.service;

import com.procurement.apps.entity.Room;
import com.procurement.apps.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> getAllRoom(){
        return roomRepository.findAll();
    }
}
