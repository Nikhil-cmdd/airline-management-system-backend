package com.accelerate.Airline.service;

import com.accelerate.Airline.config.JwtUtil;
import com.accelerate.Airline.dto.*;
import com.accelerate.Airline.enums.Role;
import com.accelerate.Airline.model.Employee;
import com.accelerate.Airline.model.Passenger;
import com.accelerate.Airline.model.User;
import com.accelerate.Airline.repository.EmployeeRepository;
import com.accelerate.Airline.repository.PassengerRepository;
import com.accelerate.Airline.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PassengerRepository passengerRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public Passenger signUpPassenger(PassengerReqDto passengerReqDto) {
        User user = new User();
        user.setUsername(passengerReqDto.username());
        String encodedPassword = passwordEncoder.encode(passengerReqDto.password());
        user.setPassword(encodedPassword);
        user.setRole(Role.PASSENGER);
        User userDB = userRepository.save(user);

        Passenger passenger = new Passenger();
        passenger.setName(passengerReqDto.name());
        passenger.setAge(passengerReqDto.age());
        passenger.setContact(passengerReqDto.contact());
        passenger.setDateOfRegistration(LocalDate.now());
        passenger.setUser(user);

        return passengerRepository.save(passenger);
    }

    public Employee signUpEmployee(@Valid EmployeeReqDto employeeReqDto) {
        User user = new User();
        user.setUsername(employeeReqDto.username());
        String encodedPassword = passwordEncoder.encode(employeeReqDto.password());
        user.setPassword(encodedPassword);
        user.setRole(Role.EMPLOYEE);
        User userDB = userRepository.save(user);

        Employee employee = new Employee();
        employee.setName(employeeReqDto.name());
        employee.setJobTitle(employeeReqDto.jobTitle());
        employee.setContact(employeeReqDto.contact());
        employee.setAge(employeeReqDto.age());
        employee.setUser(user);

        return employeeRepository.save(employee);
    }

    public LoginResponseDto login(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));

        if (user.getRole() == Role.PASSENGER) {
            Passenger passenger = passengerRepository.findByUserUsername(username);
            return new LoginResponseDto(passenger.getName(), Role.PASSENGER.toString());
        }
        else {
            Employee employee = employeeRepository.findByUserUsername(username);
            return new LoginResponseDto(employee.getName(), Role.EMPLOYEE.toString());
        }
    }

    public JwtTokenDto generateToken(UserReqDto userReqDto) {
        //Step 1: Build Authentication based on username, password
        Authentication authentication
                =  new UsernamePasswordAuthenticationToken(userReqDto.username(),userReqDto.password());

        authenticationManager.authenticate(authentication);

        String token = jwtUtil.generateToken(userReqDto.username());
        Date expiryDate = jwtUtil.extractExpiration(token);
        return new JwtTokenDto(
                userReqDto.username(),
                token,
                expiryDate,
                new Date()
        );
    }
}
