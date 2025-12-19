package com.accelerate.Airline.repository;

import com.accelerate.Airline.dto.AirlineInfoDto;
import com.accelerate.Airline.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {

    @Query("""
            select new com.accelerate.Airline.dto.AirlineInfoDto
            (a.id, a.name, f.flightNumber, f.numberOfSeats, fs.seatNumber, fs.price)
            from FlightSeat fs
            JOIN fs.flight f
            JOIN f.airline a
            where a.id=?1
            """)
    public List<AirlineInfoDto> getAirlineInfoWithFlightAndFlightSeats(int airlineId);
}
