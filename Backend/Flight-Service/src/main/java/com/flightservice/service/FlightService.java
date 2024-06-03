package com.flightservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flightservice.entity.Flight;

@Service
public interface FlightService {
	List<Flight> getAllFlights();

	Optional<Flight> getFlightById(long id);

	Flight saveFlight(Flight flight);

	Flight updateFlight(long id, Flight flight);

	String deleteFlight(long id);

}
