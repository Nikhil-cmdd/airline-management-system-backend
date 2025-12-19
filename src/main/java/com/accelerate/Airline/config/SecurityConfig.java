package com.accelerate.Airline.config;

import com.accelerate.Airline.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;
    private final JwtFilter jwtFilter;

    static{
        System.out.println("Security class Loaded");
    }

    @Bean
    public SecurityFilterChain securedFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/auth/generate/token")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/passenger/sign-up")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/employee/sign-up")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/auth/login")
                        .authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/flight/info/{flightId}")
                        .hasAnyAuthority("PASSENGER", "EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/flight/staff/{flightId}")
                        .hasAnyAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/flight/employee/airline-name")
                        .hasAnyAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/flight/airline/{airlineId}")
                        .hasAnyAuthority("PASSENGER")
                        .requestMatchers(HttpMethod.GET, "/api/flight/seat/info/{airlineId}")
                        .hasAnyAuthority("EMPLOYEE", "PASSENGER")
                        .requestMatchers(HttpMethod.GET, "/api/flight/seat/by-airline")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/flight/passengers/display-date")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/flight/passenger-details/v2/{flightId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/flight/{airlineId}")
                        .hasAnyAuthority("EMPLOYEE") //similar with POST, PUT
                        .requestMatchers(HttpMethod.GET, "/api/flight/captain/info")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/flight/active")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/flight/employee/by-airline")
                        .permitAll()


                        .anyRequest().denyAll()
                );

        //register your jwt filter with spring security filter chain
        //your filter must get priority
        //http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        //http.httpBasic(Customizer.withDefaults()); //-< instead of Http Basic Technique we replaced this with JWT technique
        return http.build();
    }

    /*
    @Bean
    public UserDetailsService users() {
        UserDetails user1 = User.builder()
                .username("employee")
                .password("{noop}employee@123")
                .roles("EMPLOYEE")
                .build();
        UserDetails user2 = User.builder()
                .username("passenger")
                .password("{noop}passenger@123")
                .roles("PASSENGER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
    **/

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(getEncoder());

        return new ProviderManager(authenticationProvider);
    }

}

/*
 * 1. CORS <-- Cross Origin Reference
 * 2. CSRF <-- Cross Site Request Forgery (For POST APIs)
 * */