package com.accelerate.Airline.dto;

import java.time.LocalDate;

public record PassengerResponseDto(
        int passengerId,
        String passengerName,
        LocalDate dateOfRegistration,
        String passengerContact,
        LocalDate bookingDate,
        String flightNumber
) {
}
