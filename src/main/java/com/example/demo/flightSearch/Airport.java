package com.example.demo.flightSearch;
import jakarta.persistence.*;

@Entity
@Table
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;

    public Airport() {
    }

    public Airport(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    public Airport(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}
