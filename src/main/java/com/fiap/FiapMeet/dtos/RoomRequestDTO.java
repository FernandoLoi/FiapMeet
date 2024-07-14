package com.fiap.FiapMeet.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RoomRequestDTO(
    @NotNull(message = "Cannot be null")
    String name,

    @Positive(message = "Only positive values")
    @NotNull(message = "Cannot be null")
    int size
){
}
