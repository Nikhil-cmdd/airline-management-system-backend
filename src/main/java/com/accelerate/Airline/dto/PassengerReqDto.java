package com.accelerate.Airline.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PassengerReqDto(
        @NotNull
        @NotBlank
        String name,
        Integer age,
        String contact,
        String username,
        String password
) {
}
