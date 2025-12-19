package com.accelerate.Airline.mapper;

import com.accelerate.Airline.dto.PassengerResponseDto;
import com.accelerate.Airline.model.Booking;

public class BookingMapper {

    public static PassengerResponseDto entityToDto(Booking booking) {
        return new PassengerResponseDto(
                booking.getPassenger().getId(),
                booking.getPassenger().getName(),
                booking.getPassenger().getDateOfRegistration(),
                booking.getPassenger().getContact(),
                booking.getBookingDate(),
                booking.getFlight().getFlightNumber()
        );
    }
}
