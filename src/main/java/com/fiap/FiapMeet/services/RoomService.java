package com.fiap.FiapMeet.services;

import com.fiap.FiapMeet.dtos.RoomRequestDTO;
import com.fiap.FiapMeet.dtos.RoomResponseDTO;
import com.fiap.FiapMeet.entities.Room;
import com.fiap.FiapMeet.mappers.RoomMapper;
import com.fiap.FiapMeet.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public Page<RoomResponseDTO> getAllRooms(Pageable pageable){
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage.map(RoomMapper::roomToRoomDTO);
    }

    @Transactional(readOnly = true)
    public Room getRoomById(UUID id){
        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room not found"));
    }

    @Transactional
    public Room createRoom(RoomRequestDTO roomRequestDTO){
        return roomRepository.save(RoomMapper.roomDTOtoRoom(roomRequestDTO));
    }
}
