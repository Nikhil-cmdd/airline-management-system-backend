package com.accelerate.Airline.model;


import com.accelerate.Airline.enums.JobTitle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;

    private String contact;
    private int age;

    @OneToOne
    private User user;
}
