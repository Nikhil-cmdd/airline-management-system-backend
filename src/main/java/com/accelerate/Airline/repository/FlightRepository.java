package com.accelerate.Airline.repository;

import com.accelerate.Airline.dto.FlightDto;
import com.accelerate.Airline.enums.SeatType;
import com.accelerate.Airline.model.Flight;
import com.accelerate.Airline.model.FlightSeat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Optional<Flight> findByIdAndActiveTrue(int flightId);

    List<Flight> findByActiveTrue(Pageable pageable); // select f from Flight f where active = true

    List<Flight> findByActiveFalse(); //select f from Flight f where active = false

    // List<FlightDto> findByIdAndActiveFalse();

    //List<Flight> findByAirlineId(int airlineId);

    List<Flight> findByAirlineIdAndActiveTrue(int airlineId); // select f from Flight f

    @Query("""
            SELECT f FROM Flight f
            JOIN f.airline a
            WHERE a.name=?1
            AND f.active = True
            """)
    List<Flight> getActiveFlightsByAirlineName(String airlineName);

    @Query("""
            SELECT fs FROM FlightSeat fs
            JOIN fs.flight f
            JOIN f.airline a
            WHERE a.id=?1 AND fs.seatType=?2
            """)
    List<FlightSeat> findSeatsByAirlineAndSeatType(int airlineId, SeatType seatType);
}
/*
* findBy : select f from Flight f
* Id     : select f from Flight f where f.id=?1
* And    : select f from Flight f where f.Id=? AND
* ActiveTrue : select f from Flight f where f.id=?1 AND t.active = true
* */
