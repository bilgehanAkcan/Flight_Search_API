package com.example.demo.flightSearch;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long flightId) {
        return flightRepository.findById(flightId);
    }

    public void addNewFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public List<Flight> searchFlights(
            Airport departureAirportId,
            Airport arrivalAirportId,
            LocalDateTime departureDateTime,
            LocalDateTime arrivalDateTime) {
        if (arrivalDateTime == null) {
            return flightRepository.findOneWayFlight(
                    departureAirportId,
                    arrivalAirportId,
                    departureDateTime);
        }
        else {
            return flightRepository.findTwoWayFlight(
                    departureAirportId,
                    arrivalAirportId,
                    departureDateTime,
                    arrivalDateTime);
        }
    }

    public void deleteFlight(Long flightId) {
        boolean check = flightRepository.existsById(flightId);
        if (!check) {
            throw new IllegalStateException("Flight with id " + flightId + " does not exist!");
        }
        else {
            flightRepository.deleteById(flightId);
        }
    }

    @Transactional
    public void updateFlight(Long flightId, Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, Float price) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new IllegalStateException("Flight with id " + flightId + " does not exist!"));

        if (price != null && price > 0 && !price.equals(flight.getPrice()))
            flight.setPrice(price);
        if (arrivalDateTime != null && !arrivalDateTime.equals(flight.getArrivalDateTime()))
            flight.setArrivalDateTime(arrivalDateTime);
        if (departureDateTime != null && !departureDateTime.equals((flight.getDepartureDateTime())))
            flight.setDepartureDateTime(departureDateTime);
        if (departureAirportId != null && !departureAirportId.equals(flight.getDepartureAirport().getId()) ) {
            Airport departedAirport = airportRepository.findById(departureAirportId).orElseThrow(() -> new IllegalStateException("Airport with departureAirportId " + departureAirportId + " does not exist!"));
            flight.setDepartureAirport(departedAirport);
        }
        if (arrivalAirportId != null && !arrivalAirportId.equals(flight.getArrivalAirport().getId())) {
            Airport arrivedAirport = airportRepository.findById(arrivalAirportId).orElseThrow(() -> new IllegalStateException("Airport with arrivalAirportId " + arrivalAirportId + " does not exist!"));
            flight.setArrivalAirport(arrivedAirport);
        }
    }
}
