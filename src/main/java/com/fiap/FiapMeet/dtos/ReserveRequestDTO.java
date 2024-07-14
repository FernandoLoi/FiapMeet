package com.fiap.FiapMeet.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record ReserveRequestDTO(
        @NotNull(message = "Cannot be null")
        UUID id_room,
        @NotNull(message = "Cannot be null")
        LocalDateTime start_date,
        @NotNull(message = "Cannot be null")
        LocalDateTime end_date,
        @Positive(message = "Only positive values")
        @NotNull(message = "Cannot be null")
        int quantity
) {
}
