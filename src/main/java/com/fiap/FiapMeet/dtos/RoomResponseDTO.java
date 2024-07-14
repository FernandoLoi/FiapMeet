package com.fiap.FiapMeet.dtos;

import java.util.UUID;

public record RoomResponseDTO(
        UUID id,
        String name,
        int size
) {
}
