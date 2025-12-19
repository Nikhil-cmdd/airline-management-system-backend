package com.accelerate.Airline.dto;


public record FlightSeatDto(

        String seatNumber,
        double price,
        String seatType,
        String flightNumber,
        String airlineName
) {
}
