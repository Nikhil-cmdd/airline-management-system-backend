package com.accelerate.Airline.repository;

import com.accelerate.Airline.dto.PassengerResponseDto;
import com.accelerate.Airline.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

//    @Query("""
//            select new com.accelerate.Airline.dto.PassengerResponseDto
//            (p.id,
//            p.name,
//            p.dateOfRegistration,
//            p.contact,
//            b.bookingDate,
//            f.flightNumber)
//            from Passenger p
//            JOIN Booking b ON p.id = b.passenger_id
//            JOIN Flight f ON b.flight_id = f.id
//            where f.id=?1
//            """)
//    List<PassengerResponseDto> getPassengerDetailsWithFlightInfo(int flightId);

    @Query("""
    select new com.accelerate.Airline.dto.PassengerResponseDto(
        p.id,
        p.name,
        p.dateOfRegistration,
        p.contact,
        b.bookingDate,
        f.flightNumber
    )
    from Booking b
    join b.passenger p
    join b.flight f
    where f.id = ?1
""")
    List<PassengerResponseDto> getPassengerDetailsWithFlightInfo(int flightId);

    @Query("""
            select new com.accelerate.Airline.dto.PassengerResponseDto
            (p.id,
            p.name,
            p.dateOfRegistration,
            p.contact,
            b.bookingDate,
            f.flightNumber)
            from Booking b
            JOIN b.passenger p
            JOIN b.flight f
            where f.id=?1
            """)
    List<PassengerResponseDto> getPassengerDetailsWithFlightInfoV2(int flightId);

    @Query("""
            select com.accelerate.Airline.dto.PassengerResponseDto
            (
            p.id,
            p.name,
            p.dateOfRegistration,
            p.contact,
            b.bookingDate,
            f.flightNumber
            )
            from Passenger p
            JOIN Booking b on p.id = b.passenger.id
            JOIN Flight f on b.flight.id = f.id
            WHERE f.airline.id = ?1
            """)
    List<PassengerResponseDto> getPassengerByAirlineIdV1(int airlineId);

    /*
    there ain't no criteria
    that is have to go from the intermediary table in which other models are injected
    and joining them from the intermediary table
     */

    @Query("""
            select new com.accelerate.Airline.dto.PassengerResponseDto
            (
            p.id,
            p.name,
            p.dateOfRegistration,
            p.contact,
            b.bookingDate,
            f.flightNumber
            )
            from Booking b
            JOIN b.passenger p
            JOIN b.flight f
            WHERE f.airline.id = ?1
            """)
    List<PassengerResponseDto> getPassengerByAirlineIdV2(int airlineId);

    @Query("""
            select new com.accelerate.Airline.dto.PassengerResponseDto
            (p.id,
            p.name,
            p.dateOfRegistration,
            p.contact,
            b.bookingDate,
            f.flightNumber)
            from Booking b
            join b.passenger p
            join b.flight f
            where f.airline.id = ?1
            """)
    List<PassengerResponseDto> getPassengerByAirlineIdV3(int airlineId);

    Passenger findByUserUsername(String username);
}
