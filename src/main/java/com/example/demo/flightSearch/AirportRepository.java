package com.example.demo.flightSearch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query("SELECT a.id FROM Airport a")
    List<Long> findAllIds();
}
