package com.fiap.FiapMeet.mappers;

import com.fiap.FiapMeet.dtos.RoomRequestDTO;
import com.fiap.FiapMeet.dtos.RoomResponseDTO;
import com.fiap.FiapMeet.entities.Room;

public abstract class RoomMapper {

    public static Room roomDTOtoRoom(RoomRequestDTO roomRequestDTO){
        Room room = new Room();

        room.setSize(roomRequestDTO.size());
        room.setName(roomRequestDTO.name());

        return room;
    }

    public static RoomResponseDTO roomToRoomDTO(Room room){
        return new RoomResponseDTO(room.getId(), room.getName(), room.getSize());
    }
}
