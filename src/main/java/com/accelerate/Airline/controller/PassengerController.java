package com.accelerate.Airline.controller;

import com.accelerate.Airline.dto.PassengerRequestDto;
import com.accelerate.Airline.model.Passenger;
import com.accelerate.Airline.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/passenger")
public class PassengerController{

    @Autowired
    private PassengerService passengerService;

    //POST Passenger
    @PostMapping
    public Passenger addPassenger(@Valid @RequestBody PassengerRequestDto passengerRequestDto){
        //Converting Dto into entity and saving...
        return passengerService.createFromDto(passengerRequestDto);
    }


}