package com.example.demo.flightSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/airports")
public class AirportController {
    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping(path = "{id}")
    public Airport getAirportById(@PathVariable Long id) {
        return airportService.getAirportById(id);
    }

    @DeleteMapping(path = "{airportId}")
    public void deleteAirport(@PathVariable Long airportId) {
        airportService.deleteAirport(airportId);
    }

    @PutMapping(path = "{airportId}")
    public void updateAirport(@PathVariable Long airportId, @RequestParam(required = false) String city) {
        airportService.updateAirport(airportId, city);
    }

}
