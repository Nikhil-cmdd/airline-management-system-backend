package com.accelerate.Airline.controller;


import com.accelerate.Airline.dto.AirlineInfoDto;
import com.accelerate.Airline.dto.FlightDto;
import com.accelerate.Airline.dto.FlightSeatDto;
import com.accelerate.Airline.dto.PassengerResponseDto;
import com.accelerate.Airline.enums.JobTitle;
import com.accelerate.Airline.enums.SeatType;
import com.accelerate.Airline.model.*;
import com.accelerate.Airline.service.FlightService;
import io.jsonwebtoken.lang.Strings;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/flight")
public class FlightController {
    private final FlightService flightService;


    @PostMapping("/{airlineId}")
    public Flight postFlight(@PathVariable int airlineId,
                    @Valid @RequestBody FlightDto flightDto){
        return flightService.postFlight(airlineId, flightDto);
    }

    @GetMapping("/all")
    public List<FlightDto> getAllFlights() {    //@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "1000") int size
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightId}")
    public FlightDto getFlightById(@PathVariable int flightId){
        return flightService.getFlightById(flightId);
    }

    @PutMapping("/{flightId}")
    public Flight updateFlight(@PathVariable int flightId,
                             @Valid @RequestBody FlightDto flightDto) {
        return flightService.updateFlight(flightId, flightDto);

    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<?> deleteFlight(@PathVariable int flightId) {
        flightService.deleteFlight(flightId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("deactivated")
    public List<FlightDto> getDeactivated() {
        return flightService.getAllDeactivatedFlights();
    }

    public List<FlightDto> getAllActiveFlights(@RequestParam(required = false, defaultValue = "0") int page,
                                               @RequestParam(required = false, defaultValue = "1000") int size)
    {
        return flightService.getAllActiveFlights(page, size);
    }

    @GetMapping("/deleted")
    public List<Flight> getAllDeletedFlights() {
        return flightService.getAllDeletedFlights();
    }

    @GetMapping("/airline/{airlineId}")
    public List<FlightDto> getFlightsByAirlineId(@PathVariable int airlineId) {
        return flightService.getFlightsByAirlineId(airlineId);
    }

    @GetMapping("info/{flightId}")
    public List<FlightSeatDto> getAllFlightSeatsInfoWithFlightAndAirlineDetails(@PathVariable int flightId) {
        return flightService.getAllFlightSeatsInfoWithFlightAndAirlineDetails(flightId);
    }

    @GetMapping("/seat/info/v2/{airlineId}")
    public List<AirlineInfoDto> getAirlineInfoWithFlightAndFlightSeats(@PathVariable int airlineId) {
        return flightService.getAirlineInfoWithFlightAndFlightSeats(airlineId);
    }

    @GetMapping("/staff/{flightId}")
    public FlightStaff getFlightStaffByFlightId(@PathVariable int flightId) {
        return flightService.getFlightStaffByFlightId(flightId);
    }

    @GetMapping("/employee")
    public List<Employee> getEmployeeByJobTitle(@RequestParam JobTitle jobTitle) {
        return flightService.getEmployeeByJobTitle(jobTitle);
    }

    @GetMapping("flightStaff/{airlineId}")
    public List<FlightStaff> getFlightStaffByAirlineId(@PathVariable int airlineId) {
        return flightService.getFlightStaffByAirlineId(airlineId);
    }

    @GetMapping("/source")
    public List<Schedule> getFlightBySource(@RequestParam String source) {
        return flightService.getFlightBySource(source);
    }

    @GetMapping("/date")
    public List<Schedule> getFlightsByTakeOfDate(@RequestParam LocalDate takeOfDate) {
        return flightService.getFlightsByTakeOfDate(takeOfDate);
    }

    @GetMapping("/destination")
    public List<Schedule> getFlightsByDestination(@RequestParam String destination) {
        return flightService.getFlightsByDestination(destination);
    }

    @GetMapping("/passenger-details/{flightId}")
    public List<PassengerResponseDto> getPassengerDetailsWithFlightInfo(@PathVariable int flightId) {
        return flightService.getPassengerDetailsWithFlightInfo(flightId);
    }

    @GetMapping("/passenger-details/v2/{flightId}")
    public List<PassengerResponseDto> getPassengerDetailsWithFlightInfoV2(@PathVariable int flightId) {
        return flightService.getPassengerDetailsWithFlightInfoV2(flightId);
    }

    @GetMapping("/passenger/v1/{airlineId}")
    public List<PassengerResponseDto> getPassengerByAirlineIdV1(@PathVariable int airlineId){
        return flightService.getPassengerByAirlineIdV1(airlineId);
    }

    @GetMapping("/passenger/v2/{airlineId}")
    public List<PassengerResponseDto> getPassengerByAirlineIdV2(@PathVariable int airlineId){
        return flightService.getPassengerByAirlineIdV2(airlineId);
    }

    @GetMapping("/passenger/v3/{airlineId}")
    public List<PassengerResponseDto> getPassengerByAirlineIdV3(@PathVariable int airlineId){
        return flightService.getPassengerByAirlineIdV3(airlineId);
    }

    @GetMapping("/captain/info")
    public Employee getCaptainInfoByFlightInfo(@RequestParam String flightNumber) {
        return flightService.getCaptainInfoByFlightInfo(flightNumber);
    }

//    @GetMapping("/bookingDate")
//    public List<PassengerRequestDto> getPassengersByBookingDate(@RequestParam LocalDate bookingDate) {
//        return flightService.getPassengersByBookingDate(bookingDate);
//    }

    @GetMapping("/active")
    public List<FlightDto> getActiveFlightsByAirlineName(@RequestParam String airlineName) {
        return flightService.getActiveFlightsByAirlineName(airlineName);
    }

    @GetMapping("/employee/airline-name")
    public List<Employee> getEmployeeByAirlineName(@RequestParam String airlineName) {
        return flightService.getEmployeeByAirlineName(airlineName);
    }

    @GetMapping("/seat/by-airline")
    public List<FlightSeat> getSeatsByAirlineAndSeatType(@RequestParam int airlineId, @RequestParam SeatType seatType) {
        return flightService.getSeatsByAirlineAndSeatType(airlineId, seatType);
    }
    @GetMapping("/airline/from-source")
    public List<String> getAirlinesBySource(@RequestParam String source) {
        return flightService.getAirlinesBySource(source);
    }
    @GetMapping("/passengers/display-date")
    public List<Passenger> getPassengersSameRegAndBookingDate(@RequestParam int flightId) {
        return flightService.getPassengersSameRegAndBookingDate(flightId);
    }

    @GetMapping("/employee/by-airline")
    public List<String> getEmployeesByAirline(@RequestParam String airlineName) {
        return flightService.getEmployeesByAirline(airlineName);
    }
}
