package com.fiap.FiapMeet.dtos;

import jakarta.validation.constraints.Positive;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DateUpdateRequestDTO (
        LocalDateTime start_date,
        LocalDateTime end_date
) {
}
