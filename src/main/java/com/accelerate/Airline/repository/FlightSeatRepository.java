package com.accelerate.Airline.repository;

import com.accelerate.Airline.model.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightSeatRepository extends JpaRepository<FlightSeat, Integer> {
    List<FlightSeat> findByFlightId(int flightId);
}
