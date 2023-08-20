package com.example.demo.flightSearch;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FlightGenerationService {
    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    @Autowired
    public FlightGenerationService(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }
    
    @Scheduled(fixedRate = 86400000) // The frequency at which the scheduled background job will run can be adjusted here. Right now, in every 24 hours (in terms of miliseconds), the background job is run.
    public void generateAndInsertFlightData() {
        for (int i = 0; i < 5; i++) {
            Flight flight = generateRandomFlight();
            flightRepository.save(flight);
        }
    }

    private Flight generateRandomFlight() {
        List<Long> airportIds = airportRepository.findAllIds();
        
        Long departureAirportId = airportIds.get(new Random().nextInt(airportIds.size()));
        Long arrivalAirportId = airportIds.get(new Random().nextInt(airportIds.size()));
        Airport departureAirport = airportRepository.findById(departureAirportId).orElse(null);
        Airport arrivalAirport = airportRepository.findById(arrivalAirportId).orElse(null);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departureDateTime = now.plusDays(new Random().nextInt(30)).plusHours(new Random().nextInt(24)).withNano(0);
        LocalDateTime arrivalDateTime = departureDateTime.plusHours(new Random().nextInt(12) + 1).withNano(0);

        Float price = 100 + new Random().nextFloat() * 4900;
        
        return new Flight(departureAirport, arrivalAirport, departureDateTime, arrivalDateTime, price);
    }
}
