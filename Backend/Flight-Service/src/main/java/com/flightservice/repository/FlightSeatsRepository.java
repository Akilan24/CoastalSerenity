package com.flightservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightservice.entity.FlightSeats;

@Repository
public interface FlightSeatsRepository extends JpaRepository<FlightSeats, Long>{

}
