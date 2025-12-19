package com.accelerate.Airline.controller;

import com.accelerate.Airline.dto.*;
import com.accelerate.Airline.model.Employee;
import com.accelerate.Airline.model.Passenger;
import com.accelerate.Airline.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/passenger/sign-up")
    public Passenger signUpPassenger(@Valid @RequestBody PassengerReqDto passengerReqDto) {
        return authService.signUpPassenger(passengerReqDto);
    }

    @PostMapping("/employee/sign-up")
    public Employee signUpEmployee(@Valid @RequestBody EmployeeReqDto employeeReqDto) {
        return authService.signUpEmployee(employeeReqDto);
    }

    /*
    Any user that reaches this method has already given CORRECT username/password
    So lets ask spring the username of this user
    **/
    @GetMapping("/login")
    public LoginResponseDto login(Principal principal) { //Dependency Injection
        String username = principal.getName();
        return authService.login(username);
    }

    @PostMapping("/generate/token")
    public JwtTokenDto generateToken(@RequestBody UserReqDto userReqDto){
        return authService.generateToken(userReqDto);
    }
}
