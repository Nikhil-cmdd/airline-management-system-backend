package com.accelerate.Airline.mapper;

import com.accelerate.Airline.dto.FlightSeatDto;
import com.accelerate.Airline.model.FlightSeat;
import org.springframework.stereotype.Component;

@Component
public class FlightSeatMapper {

    public static FlightSeatDto entityToDto(FlightSeat flightSeat) {
        return new FlightSeatDto(
                flightSeat.getSeatNumber(),
                flightSeat.getPrice(),
                flightSeat.getSeatType().toString(),
                flightSeat.getFlight().getFlightNumber(),
                flightSeat.getFlight().getAirline().getName()
        );
    }

}
