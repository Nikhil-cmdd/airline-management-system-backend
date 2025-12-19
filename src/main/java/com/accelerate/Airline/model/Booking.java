package com.accelerate.Airline.model;

import com.accelerate.Airline.model.Flight;
import com.accelerate.Airline.model.FlightSeat;
import com.accelerate.Airline.model.Passenger;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking { // passenger_flight  (junction table)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // findById

    @ManyToOne
    private Passenger passenger; // 1 passenger : M passenger_flight

    @ManyToOne
    private Flight flight; // 1 flight : M passenger_flight

    @CreatedDate
    private LocalDate bookingDate;

    @Column(name = "actual_cost_paid")
    private double actualCostPaid;

    @Column(name = "discount_coupon")
    private String discountCoupon;
}
