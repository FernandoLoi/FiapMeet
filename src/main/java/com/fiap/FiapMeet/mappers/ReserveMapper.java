package com.fiap.FiapMeet.mappers;

import com.fiap.FiapMeet.dtos.ReserveRequestDTO;
import com.fiap.FiapMeet.dtos.ReserveResponseDTO;
import com.fiap.FiapMeet.entities.Reserve;

public abstract class ReserveMapper {

    public static Reserve reserveDTOtoReserve(ReserveRequestDTO reserveRequestDTO){
        Reserve reserve = new Reserve();

        reserve.setId_room(reserveRequestDTO.id_room());
        reserve.setQuantity(reserveRequestDTO.quantity());
        reserve.setStart_date(reserveRequestDTO.start_date());
        reserve.setEnd_date(reserveRequestDTO.end_date());

        return reserve;
    }

    public static ReserveResponseDTO reserveToReserveDTO(Reserve reserve){
        return new ReserveResponseDTO(reserve.getId(), reserve.getId_room(),reserve.getStart_date(), reserve.getEnd_date(),reserve.getQuantity());
    }
}
