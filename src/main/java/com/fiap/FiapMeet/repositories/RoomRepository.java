package com.fiap.FiapMeet.repositories;

import com.fiap.FiapMeet.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    Page<Room> findAll(Pageable pageable);
}
