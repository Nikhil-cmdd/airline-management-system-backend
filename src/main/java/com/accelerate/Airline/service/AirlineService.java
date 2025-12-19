package com.accelerate.Airline.service;


import com.accelerate.Airline.model.Airline;
import com.accelerate.Airline.repository.AirlineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AirlineService {

    private final AirlineRepository airlineRepository;

    public Airline getAirlineById(int airlineId) {
        return airlineRepository.findById(airlineId)
                .orElseThrow(() -> new RuntimeException("Airline with id " + airlineId + " not found"));
    }
}
