package com.accelerate.Airline.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flights")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String flightNumber;

    private int numberOfSeats;

    @ManyToOne
    private Airline airline;

    private boolean active = true;
}
