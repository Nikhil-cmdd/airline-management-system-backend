package com.accelerate.Airline.dto;

import java.util.Date;

public record JwtTokenDto(
        String username,
        String token,
        Date expiryDate,
        Date generatedAt
) {
}