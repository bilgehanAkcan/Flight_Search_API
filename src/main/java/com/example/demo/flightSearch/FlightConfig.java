package com.example.demo.flightSearch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;

@Configuration
public class FlightConfig {
    @Bean
    CommandLineRunner commandLineRunner(FlightRepository flightRepository) {
        return args -> {
            Flight flight1 = new Flight("Esenboga Airport", "Sabiha Gokcen Airport", LocalDate.of(2023, Month.AUGUST, 18), LocalDate.of(2023, Month.AUGUST, 19), 150F);
            Flight flight2 = new Flight("Esenboga Airport", "Istanbul Airport", LocalDate.of(2023, Month.SEPTEMBER, 22), LocalDate.of(2023, Month.SEPTEMBER, 22), 250F);

            flightRepository.saveAll(List.of(flight1, flight2));
        };
    }
}
