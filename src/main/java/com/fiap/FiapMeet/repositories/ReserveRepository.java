package com.fiap.FiapMeet.repositories;

import com.fiap.FiapMeet.entities.Reserve;
import com.fiap.FiapMeet.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReserveRepository extends JpaRepository<Reserve, UUID> {
    Page<Reserve> findAll(Pageable pageable);
    void deleteById (UUID id);
    Reserve findReserveById(UUID id);
}
