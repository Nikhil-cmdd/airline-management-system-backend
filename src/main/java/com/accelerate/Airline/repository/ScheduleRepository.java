package com.accelerate.Airline.repository;

import com.accelerate.Airline.dto.FlightDto;
import com.accelerate.Airline.dto.PassengerResponseDto;
import com.accelerate.Airline.model.Passenger;
import com.accelerate.Airline.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("""
            select s FROM Schedule s
            JOIN s.flight f
            JOIN f.airline a
            WHERE s.source=?1
            """)
    List<Schedule> getFlightBySource(String source);

    @Query("""
            select s FROM Schedule s
            JOIN s.flight f
            JOIN f.airline a
            WHERE s.takeOfDate=?1
            """)
    List<Schedule> getFlightsByTakeOfDate(LocalDate takeOfDate);

    @Query("""
            select s FROM Schedule s
            JOIN s.flight f
            JOIN f.airline a
            WHERE s.destination=?1
            """)
    List<Schedule> getFlightsByDestination(String destination);

    @Query("""
            select p FROM Booking b
            JOIN b.passenger p
            WHERE b.flight.id IN (
            select s.flight.id
            from Schedule s
            WHERE s.source=?1 and s.takeOfDate=?2)
            """)
    List<Passenger> getPassengerDetailsWithSourceAndDate(String source, LocalDate takeOfDate);

    @Query("""
            SELECT a.name FROM Schedule s
            JOIN s.flight f
            JOIN f.airline a
            WHERE s.source=?!
            """)
    List<String> findAirlinesBySource(String source);
}
