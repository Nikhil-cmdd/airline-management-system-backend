package com.accelerate.Airline.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "flight_staff")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name ="captain_id")
    private Employee captain;  // should have jobTitle = CAPTAIN


    @OneToOne
    @JoinColumn (name ="asst_captain_id")
    private Employee asstCaptain;  //should have jobTitle = ASST_CAPTAIN

    @OneToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
