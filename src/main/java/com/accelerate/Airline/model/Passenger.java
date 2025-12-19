package com.accelerate.Airline.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "passengers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private Integer age;
    private String contact;

    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @PrePersist
    public void onCreate() {
        this.dateOfRegistration = LocalDate.now();
    }

    @OneToOne
    private User user;
}
