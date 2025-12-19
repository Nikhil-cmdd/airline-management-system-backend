package com.accelerate.Airline.mapper;

import com.accelerate.Airline.dto.FlightDto;
import com.accelerate.Airline.model.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public Flight dtoToEntity(FlightDto flightDto){
        Flight flight = new Flight();
        flight.setFlightNumber(flightDto.flightNumber());
        flight.setNumberOfSeats(flightDto.numberOfSeats());
        return flight;
    }

    public FlightDto entityToDto (Flight flight){
        return new FlightDto(

                flight.getFlightNumber(),
//                flight.getAirline().getName()
                flight.getNumberOfSeats()
        );

    }

}
