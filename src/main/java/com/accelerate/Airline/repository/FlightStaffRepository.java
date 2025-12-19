package com.accelerate.Airline.repository;

import com.accelerate.Airline.model.FlightStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightStaffRepository extends JpaRepository<FlightStaff, Integer> {
    @Query("""
            select fs from FlightStaff fs
            JOIN fs.flight f
            where f.id=?1
            """)

    FlightStaff getFlightStaffByFlightId(int flightId);

    @Query("""
           select fs from FlightStaff fs
           JOIN fs.flight f
           JOIN f.airline a
           WHERE a.id= :airlineId
           """)
    List<FlightStaff> getFlightStaffByAirlineId(@Param("airlineId") int airlineId);
}
