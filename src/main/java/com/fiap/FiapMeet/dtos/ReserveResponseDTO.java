package com.fiap.FiapMeet.dtos;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record ReserveResponseDTO(
        UUID id,
        UUID id_room,
        LocalDateTime start_date,
        LocalDateTime end_date,
        int quantity
) {
}
