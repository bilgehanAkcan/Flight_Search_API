package com.example.demo.flightSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/flights")
public class FlightController {
    private final FlightService flightService;
    private final AirportService airportService;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService) {
        this.flightService = flightService;
        this.airportService = airportService;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getFlights();
    }

    @GetMapping(path = "{flightId}")
    public Optional<Flight> getFlightById(@PathVariable Long flightId) {
        return flightService.getFlightById(flightId);
    }

    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam Long departureAirportId,
            @RequestParam Long arrivalAirportId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDateTime
    ) {
        Airport departureAirport = null;
        if (departureAirportId != null) {
            departureAirport = airportService.getAirportById(departureAirportId);
        }
        Airport arrivalAirport = null;
        if (arrivalAirportId != null) {
            arrivalAirport = airportService.getAirportById(arrivalAirportId);
        }
        return flightService.searchFlights(
                departureAirport,
                arrivalAirport,
                departureDateTime,
                arrivalDateTime
        );
    }

    @DeleteMapping(path = "{flightId}")
    public void deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
    }

    @PutMapping(path = "{flightId}")
    public void updateFlight(
            @PathVariable Long flightId,
            @RequestParam(required = false) Float price,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDateTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @RequestParam(required = false) Long arrivalAirportId,
            @RequestParam(required = false) Long departureAirportId) {
        flightService.updateFlight(flightId, departureAirportId, arrivalAirportId, departureDateTime, arrivalDateTime, price);
    }

    @PostMapping
    public void addFlight(@RequestBody Flight flight) {
        flightService.addNewFlight(flight);
    }
}
