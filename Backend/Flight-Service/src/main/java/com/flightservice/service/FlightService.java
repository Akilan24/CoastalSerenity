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
	List<Flight> getAllFlight();

	Optional<Flight> getFlightByFlightId(long id);

	Flight saveFlight(Flight flight);

	FlightBookingDetails bookFlight(long id, FlightTravellerFlightSeats ftfs, String username);

	FlightBookingDetails paymentStatusChangeByFlightBookingId(long bookingid);

	Flight updateFlightByFlightId(long id, Flight flight);

	String deleteFlightByFlightId(long id);

	FlightBookingDetails cancelPaymentByFlightBookingId(long id);

	List<List<String>> getAllCityNames();

	List<Flight> getAllAvailableFlights(String from, String to, Date departure, String travellerClass);

	Flight addSeatsByFlightId(long id);

	FlightBookingDetails getFlightBookingDetailsByFlightBookingId(long id);

	List<FlightBookingDetails> getFlightBookingDetailsByUsername(String username);

}
