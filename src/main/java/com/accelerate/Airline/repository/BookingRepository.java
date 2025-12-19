package com.accelerate.Airline.repository;

import com.accelerate.Airline.dto.PassengerResponseDto;
import com.accelerate.Airline.model.Booking;
import com.accelerate.Airline.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query
            ("""
            select new com.accelerate.Airline.dto.PassengerResponseDto(
            p.id,
            p.name,
            p.dateOfRegistration,
            p.contact,
            b.bookingDate,
            f.flightNumber
            )
            FROM Booking b
            JOIN b.passenger p
            JOIN b.flight f
            JOIN f.airline a
            WHERE a.id = ?1
            """)
    List<PassengerResponseDto> getPassengerDetailsWithAirlineInfo(int airlineId);


    List<Booking> findByFlightId(int flightId);

    @Query
            ("""
            select b
            from Booking b
            join b.flight f
            join f.airline a
            where a.id = ?1
            """)
    List<Booking> findByAirlineId(int airlineId);

    @Query("""
            SELECT p FROM Booking b
            JOIN b.passenger p
            WHERE b.flight.id=?1 AND p.dateOfRegistration=b.bookingDate
            """)
    List<Passenger> findPassengersWithSameRegAndBookingDate(int flightId);

//    @Query("""
//            SELECT b.passenger
//            FROM Booking b
//            where b.createdAt=?1
//            """)
//    List<PassengerRequestDto> getPassengersByBookingDate(LocalDate bookingDate);

}
