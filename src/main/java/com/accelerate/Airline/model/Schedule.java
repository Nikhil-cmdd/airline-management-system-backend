package com.accelerate.Airline.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate takeOfDate;
    private LocalTime takeOfTime;
    private String source;
    private String destination;
    private Instant arrivalTime;

    @ManyToOne
    private Flight flight; // 1 flight : M schedules

//    @ManyToOne
//    private FlightStaff flightStaff; // schedule -> flight_staff

}
