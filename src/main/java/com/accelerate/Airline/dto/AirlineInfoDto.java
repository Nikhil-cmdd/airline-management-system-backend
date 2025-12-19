package com.accelerate.Airline.dto;

import com.accelerate.Airline.enums.SeatType;
import org.springframework.stereotype.Component;

public record AirlineInfoDto(
        int airlineId,
        String airlineName,
        String flightNumber,
        Integer numberOfSeats,
        String seatNumber,
        double price
) {
}
