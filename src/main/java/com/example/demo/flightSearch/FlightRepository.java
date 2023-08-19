package com.example.demo.flightSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository  extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f " +
            "WHERE (f.departureAirport = :departureAirport) " +
            "AND (f.arrivalAirport = :arrivalAirport) " +
            "AND (f.departureDateTime = :startDate)")
    List<Flight> findOneWayFlight(
            Airport departureAirport,
            Airport arrivalAirport,
            LocalDateTime startDate
    );

    @Query("SELECT f FROM Flight f " +
            "WHERE ((f.departureAirport = :departureAirport) " +
            "AND (f.arrivalAirport = :arrivalAirport) " +
            "AND (f.departureDateTime = :startDate)) " +
            "OR ((f.departureAirport = :arrivalAirport)" +
            "AND (f.arrivalAirport = :departureAirport)" +
            "AND (f.departureDateTime = :endDate))")
    List<Flight> findTwoWayFlight(
            Airport departureAirport,
            Airport arrivalAirport,
            LocalDateTime startDate,
            LocalDateTime endDate
    );


}
