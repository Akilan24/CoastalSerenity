package com.flightservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flightservice.entity.Flight;
import com.flightservice.entity.FlightBookingDetails;
import com.flightservice.entity.FlightSeats;
import com.flightservice.externalclass.Traveller;

@Service
public interface FlightService {
	List<Flight> getAllFlights();

	Optional<Flight> getFlightById(long id);

	Flight saveFlight(Flight flight);
	
	List<FlightBookingDetails> bookFlight(long id,List<Traveller> traveller,List<FlightSeats> flightSeats,String username);
	
	List<FlightBookingDetails> paymentstatuschange(String username);

	Flight updateFlight(long id, Flight flight);

	String deleteFlight(long id);

	Flight resetStatus(long id);

	List<List<String>> getAllCityNames();

	List<Flight> getAllAvailableFlights(String from, String to, Date departure, String travellerClass);
	
	Flight addSeats(long id);

}
