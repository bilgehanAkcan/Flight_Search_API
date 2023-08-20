package com.example.demo.flightSearch;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public void deleteAirport(Long airportId) {
        boolean check = airportRepository.existsById(airportId);
        if (!check) {
            throw new IllegalStateException("Airport with id " + airportId + " does not exist!");
        }
        else {
            airportRepository.deleteById(airportId);
        }
    }

    @Transactional
    public void updateAirport(Long airportId, String city) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new IllegalStateException("Airport with id " + airportId + " does not exist!"));
        if (city != null && city.length() > 0 && !city.equals(airport.getCity())) {
            airport.setCity(city);
        }
    }
}
