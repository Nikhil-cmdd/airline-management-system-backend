package com.accelerate.Airline.model;


import com.accelerate.Airline.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "flight_seats")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "seat_number")
    private String seatNumber;
    private double price;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    private Flight flight;

}
