package com.fiap.FiapMeet.services;

import com.fiap.FiapMeet.dtos.ReserveRequestDTO;
import com.fiap.FiapMeet.dtos.ReserveResponseDTO;
import com.fiap.FiapMeet.entities.Reserve;
import com.fiap.FiapMeet.entities.Room;
import com.fiap.FiapMeet.mappers.ReserveMapper;
import com.fiap.FiapMeet.repositories.ReserveRepository;
import com.fiap.FiapMeet.repositories.RoomRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public Page<ReserveResponseDTO> getAllReserves(Pageable pageable){
        Page<Reserve> reservePage = reserveRepository.findAll(pageable);
        return reservePage.map(ReserveMapper::reserveToReserveDTO);
    }

    @Transactional(readOnly = true)
    public Reserve getReserveById(UUID id){
        return reserveRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserve not found"));
    }

    @Transactional
    public Reserve createReserve(ReserveRequestDTO reserveRequestDTO){
        Reserve reserve = new Reserve();
        Room room = roomRepository.findById(UUID.fromString(String.valueOf(reserveRequestDTO.id_room()))).orElseThrow(() -> new EntityNotFoundException("Room not found"));

        int size = room.getSize();
        if(size < reserve.getQuantity()){
            throw new EntityNotFoundException("Room does not support");
        }
        reserve.setQuantity(reserveRequestDTO.quantity());
        reserve.setId_room(reserveRequestDTO.id_room());
        reserve.setStart_date(reserveRequestDTO.start_date());
        reserve.setEnd_date(reserveRequestDTO.end_date());

        return reserveRepository.save(reserve);
    }
}
