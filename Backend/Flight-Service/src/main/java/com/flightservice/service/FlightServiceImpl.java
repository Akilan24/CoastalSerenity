package com.flightservice.service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightservice.entity.Flight;
import com.flightservice.exception.FlightDetailsNotFoundException;
import com.flightservice.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Override
	public List<Flight> getAllFlights() {
		if (!flightRepository.findAll().isEmpty())
			return flightRepository.findAll();
		else
			throw new FlightDetailsNotFoundException("Flight details are not found");
	}

	@Override
	public Optional<Flight> getFlightById(long id) {
		Optional<Flight> flight = flightRepository.findById(id);
		if (!flight.isEmpty())
			return flight;
		else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");
	}

	@Override
	public Flight saveFlight(Flight flight) {
		long MIN_id = 100000;
		int count = flightRepository.findAll().size();
		flight.setFlightId(count == 0 ? MIN_id : MIN_id + count);
		String[] parts = flight.getDuration().split(":");
		long hours = Long.parseLong(parts[0]);
		long minutes = Long.parseLong(parts[1]);
		flight.setArrivalTime(flight.getDepartureTime().plus(Duration.ofHours(hours).plusMinutes(minutes)));
		return flightRepository.save(flight);
	}

	@Override
	public String deleteFlight(long id) {
		Optional<Flight> flight = flightRepository.findById(id);
		if (!flight.isEmpty()) {
			flightRepository.deleteById(id);
			return "Flight details are deleted";
		} else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");

	}

	@Override
	public Flight updateFlight(long id, Flight flight) {
		Optional<Flight> f = flightRepository.findById(id);
		if (!f.isEmpty()) {
			f.get().setAirline(flight.getAirline());
			f.get().setArrivalTime(flight.getArrivalTime());
			f.get().setDepartureTime(flight.getDepartureTime());
			f.get().setDestination(flight.getDestination());
			f.get().setOrigin(flight.getOrigin());
			f.get().setDuration(flight.getDuration());
			f.get().setAirlineLogo(flight.getAirlineLogo());
			f.get().setFlightModel(flight.getFlightModel());
			f.get().setSeatCount(flight.getSeatCount());
			return flightRepository.save(f.get());
		} else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");

	}
}
