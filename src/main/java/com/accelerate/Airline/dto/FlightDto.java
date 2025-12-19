package com.accelerate.Airline.dto;


import jakarta.validation.constraints.NotNull;

public record FlightDto(

        @NotNull
        String flightNumber,
        Integer numberOfSeats
) {
}
