package com.fiap.FiapMeet.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fiap.FiapMeet.dtos.RoomRequestDTO;
import com.fiap.FiapMeet.dtos.RoomResponseDTO;
import com.fiap.FiapMeet.entities.Room;
import com.fiap.FiapMeet.mappers.RoomMapper;
import com.fiap.FiapMeet.services.RoomService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.awt.*;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<Page<RoomResponseDTO>> getAllRooms(Pageable pageable){
        Page<RoomResponseDTO> rooms = roomService.getAllRooms(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoom(@PathVariable @Valid UUID id){
        Room room = roomService.getRoomById(id);
        return ResponseEntity.status(HttpStatus.OK).body(RoomMapper.roomToRoomDTO(room));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerRoom(@RequestBody @Valid RoomRequestDTO roomRequestDTO) throws JSONException {
        Room room = roomService.createRoom(roomRequestDTO);

        JSONObject response = new JSONObject();
        response.put("Id", room.getId());
        response.put("Name", room.getName());
        response.put("Size", room.getSize());
        response.put("message","Room created");

        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }
}
