package com.accelerate.Airline.service;


import com.accelerate.Airline.dto.AirlineInfoDto;
import com.accelerate.Airline.dto.FlightDto;
import com.accelerate.Airline.dto.FlightSeatDto;
import com.accelerate.Airline.dto.PassengerResponseDto;
import com.accelerate.Airline.enums.JobTitle;
import com.accelerate.Airline.enums.SeatType;
import com.accelerate.Airline.exceptions.ResourceNotFoundException;
import com.accelerate.Airline.mapper.BookingMapper;
import com.accelerate.Airline.mapper.FlightSeatMapper;
import com.accelerate.Airline.model.*;
import com.accelerate.Airline.repository.*;
import com.accelerate.Airline.mapper.FlightMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightMapper flightMapper;
    private final AirlineService airlineService;
    private final FlightRepository flightRepository;
    private final FlightSeatRepository flightSeatRepository;
    private final AirlineRepository airlineRepository;
    private final FlightStaffRepository flightStaffRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduleRepository scheduleRepository;
    private final PassengerRepository passengerRepository;
    private final BookingRepository bookingRepository;

    public Flight postFlight(int airlineId, @Valid FlightDto flightDto) {

        Flight flight = flightMapper.dtoToEntity(flightDto);

        Airline airline = airlineService.getAirlineById(airlineId);
        flight.setAirline(airline); // attach airline to flight

        return flightRepository.save(flight);
        
    }

    public List<FlightDto> getAllFlights() {
        List<Flight> list = flightRepository.findAll();
        return list
                .stream()
                .filter(Flight::isActive)
                .map(flightMapper::entityToDto)
                .toList();

    }

    public FlightDto getFlightById(int flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(()->new ResourceNotFoundException("Flight Does not exist"));
        return flightMapper.entityToDto(flight);

    }


    public Flight updateFlight(int flightId, @Valid FlightDto flightDto) {
        Flight flightDb = flightRepository.findById(flightId)
                .orElseThrow(()->new ResourceNotFoundException("Flight Does not exist"));

        Flight flightUi = flightMapper.dtoToEntity(flightDto);
        flightDb.setFlightNumber(flightUi.getFlightNumber());
        flightDb.setNumberOfSeats(flightUi.getNumberOfSeats());

        return flightRepository.save(flightDb);
    }

    public void deleteFlight(int flightId) {
        Flight flight = flightRepository.findByIdAndActiveTrue(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight does not exist"));

        flight.setActive(false);
        flightRepository.save(flight);
    }

    public List<FlightDto> getAllDeactivatedFlights() {
        List<Flight> list = flightRepository.findByActiveFalse();
        return list
                .stream()
                .map(flightMapper :: entityToDto)
                .toList();
    }

    public List<FlightDto> getAllActiveFlights(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Flight> list = flightRepository.findByActiveTrue(pageable);
        return list
                .stream()
                .map(flightMapper :: entityToDto)
                .toList();
    }

    public List<Flight> getAllDeletedFlights() {
        return flightRepository.findByActiveFalse();
    }

    public List<FlightDto> getFlightsByAirlineId(int airlineId) {
        airlineService.getAirlineById(airlineId);
        List<Flight> list = flightRepository.findByAirlineIdAndActiveTrue(airlineId);

        return list
                .stream()
                .map(flightMapper :: entityToDto)
                .toList();
    }

    public List<FlightSeatDto> getAllFlightSeatsInfoWithFlightAndAirlineDetails(int flightId) {
        Flight flight = flightRepository.findByIdAndActiveTrue(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight does not exist"));

        List<FlightSeat> flightSeatDb = flightSeatRepository.findByFlightId(flightId);
        return flightSeatDb
                .stream()
                .map(FlightSeatMapper :: entityToDto)
                .toList();
    }

    public List<AirlineInfoDto> getAirlineInfoWithFlightAndFlightSeats(int airlineId) {
        Airline airline = airlineService.getAirlineById(airlineId);
        return airlineRepository.getAirlineInfoWithFlightAndFlightSeats(airlineId);

    }

    public FlightStaff getFlightStaffByFlightId(int flightId) {
        Flight flight = flightRepository.findByIdAndActiveTrue(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight does not exist"));

        return flightStaffRepository.getFlightStaffByFlightId(flightId);
    }

    public List<Employee> getEmployeeByJobTitle(JobTitle jobTitle) {
        return employeeRepository.getEmployeeByJobTitle(jobTitle);
    }

    public List<FlightStaff> getFlightStaffByAirlineId(int airlineId) {
        return flightStaffRepository.getFlightStaffByAirlineId(airlineId);
    }

    public List<Schedule> getFlightBySource(String source) {
        return scheduleRepository.getFlightBySource(source);
    }

    public List<Schedule> getFlightsByTakeOfDate(LocalDate takeOfDate) {
        return scheduleRepository.getFlightsByTakeOfDate(takeOfDate);
    }

    public List<Schedule> getFlightsByDestination(String destination) {
        return scheduleRepository.getFlightsByDestination(destination);
    }

    public List<PassengerResponseDto> getPassengerDetailsWithFlightInfo(int flightId) {
        Flight flight = flightRepository.findByIdAndActiveTrue(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight does not exist"));

        return passengerRepository.getPassengerDetailsWithFlightInfo(flightId);
    }

    public List<PassengerResponseDto> getPassengerDetailsWithFlightInfoV2(int flightId) {
        Flight flight = flightRepository.findByIdAndActiveTrue(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight does not exist"));

        List<Booking> list = bookingRepository.findByFlightId(flightId);

        return list.stream()
                .map(BookingMapper :: entityToDto)
                .toList();
    }

    public List<PassengerResponseDto> getPassengerByAirlineIdV1(int airlineId) { // jpql with criteria method
        Airline airline = airlineService.getAirlineById(airlineId);
        return passengerRepository.getPassengerByAirlineIdV1(airlineId);
    }

    public List<PassengerResponseDto> getPassengerByAirlineIdV2(int airlineId) { // jpql without criteria
        Airline airline = airlineService.getAirlineById(airlineId);
        return passengerRepository.getPassengerByAirlineIdV2(airlineId);
    }

    public List<PassengerResponseDto> getPassengerByAirlineIdV3(int airlineId) { // derived queries
        Airline airline = airlineService.getAirlineById(airlineId);
        List<Booking> list = bookingRepository.findByAirlineId(airlineId);

        return passengerRepository.getPassengerByAirlineIdV3(airlineId);
    }

    public Employee getCaptainInfoByFlightInfo(String flightNumber) {
        return employeeRepository.getCaptainInfoByFlightInfo(flightNumber);
    }

    public List<FlightDto> getActiveFlightsByAirlineName(String airlineName) {
        List<Flight> flights = flightRepository.getActiveFlightsByAirlineName(airlineName);

        return flights.stream()
                .map(flightMapper::entityToDto)
                .toList();
    }

    public List<Employee> getEmployeeByAirlineName(String airlineName) {
        return employeeRepository.findEmployeeByAirlineName(airlineName);
    }

    public List<FlightSeat> getSeatsByAirlineAndSeatType(int airlineId, SeatType seatType) {
        return flightRepository.findSeatsByAirlineAndSeatType(airlineId, seatType);
    }

    public List<String> getAirlinesBySource(String source) {
        return scheduleRepository.findAirlinesBySource(source);
    }

    public List<Passenger> getPassengersSameRegAndBookingDate(int flightId) {
        return bookingRepository.findPassengersWithSameRegAndBookingDate(flightId);
    }

    public List<String> getEmployeesByAirline(String airlineName) {
        return employeeRepository.findEmployeesByAirline(airlineName);
    }

//    public List<PassengerRequestDto> getPassengersByBookingDate(LocalDate bookingDate) {
//        return bookingRepository.getPassengersByBookingDate(bookingDate);
//    }
}
