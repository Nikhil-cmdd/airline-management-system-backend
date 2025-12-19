package com.accelerate.Airline.repository;

import com.accelerate.Airline.enums.JobTitle;
import com.accelerate.Airline.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("""
            select e FROM Employee e where e.jobTitle=?1
            """)
    List<Employee> getEmployeeByJobTitle(JobTitle jobTitle);

    Employee findByUserUsername(String username);

    @Query("""
            select fs.captain FROM FlightStaff fs
            JOIN fs.flight f
            WHERE f.flightNumber=?1
            """)
    Employee getCaptainInfoByFlightInfo(String flightNumber);

    @Query("""
            SELECT e FROM FlightStaff fs
            JOIN fs.flight f
            JOIN f.airline a
            JOIN Employee e ON fs.captain
            WHERE a.name=?1
            """)
    List<Employee> findEmployeeByAirlineName(String airlineName);

    @Query("""
            SELECT e.name FROM FlightStaff fs
            JOIN fs.flight f
            JOIN f.airline a
            JOIN Employee e ON e=fs.captain OR e=fs.asstCaptain
            WHERE a.name=?1
            """)
    List<String> findEmployeesByAirline(String airlineName);
}
