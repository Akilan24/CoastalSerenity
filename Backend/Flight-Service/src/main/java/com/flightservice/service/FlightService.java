package com.flightservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flightservice.entity.Flight;
import com.flightservice.entity.FlightBookingDetails;

@Service
public interface FlightService {
	List<Flight> getAllFlights();

	Optional<Flight> getFlightById(long id);

	Flight saveFlight(Flight flight);
	
	FlightBookingDetails bookFlight(FlightBookingDetails flightBookingDetails);

	Flight updateFlight(long id, Flight flight);

	String deleteFlight(long id);

	Flight resetStatus(long id);

	List<List<String>> getAllCityNames();

	List<Flight> getAllAvailableFlights(String from, String to, Date departure, String travellerClass);
	
	Flight addSeats(long id);

}
