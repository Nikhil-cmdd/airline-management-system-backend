package com.accelerate.Airline.dto;

import com.accelerate.Airline.enums.JobTitle;

public record EmployeeReqDto(
        String name,
        JobTitle jobTitle,
        String contact,
        int age,
        String username,
        String password
) {
}
