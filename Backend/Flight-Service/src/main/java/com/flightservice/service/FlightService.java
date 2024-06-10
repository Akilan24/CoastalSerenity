package com.flightservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flightservice.entity.Flight;
import com.flightservice.entity.FlightBookingDetails;
import com.flightservice.entity.FlightTravellerFlightSeats;

@Service
public interface FlightService {
	List<Flight> getAllFlights();

	Optional<Flight> getFlightById(long id);

	Flight saveFlight(Flight flight);

	FlightBookingDetails bookFlight(long id, FlightTravellerFlightSeats ftfs, String username);

	FlightBookingDetails paymentstatuschange(long bookingid);

	Flight updateFlight(long id, Flight flight);

	String deleteFlight(long id);

	FlightBookingDetails resetStatus(long id);

	List<List<String>> getAllCityNames();

	List<Flight> getAllAvailableFlights(String from, String to, Date departure, String travellerClass);

	Flight addSeats(long id);

	FlightBookingDetails getFlightBookingDetailsById(long id);

	List<FlightBookingDetails> getFlightBookingDetailsByUsername(String username);

}
