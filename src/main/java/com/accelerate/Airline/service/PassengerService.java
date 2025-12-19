package com.accelerate.Airline.service;


import com.accelerate.Airline.dto.PassengerRequestDto;
import com.accelerate.Airline.model.Passenger;
import com.accelerate.Airline.repository.PassengerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;


@Service
public class PassengerService{

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger save (Passenger  passenger){
        return passengerRepository.save(passenger);
    }

    public Passenger createFromDto(@Valid PassengerRequestDto passengerRequestDto) {
        Passenger passenger = new Passenger();
        passenger.setName(passengerRequestDto.getName());
        passenger.setAge(passengerRequestDto.getAge());
        passenger.setContact(passengerRequestDto.getContact());
        return save(passenger);
    }
}