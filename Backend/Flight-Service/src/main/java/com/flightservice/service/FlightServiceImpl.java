package com.flightservice.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
		List<Flight> list = flightRepository.findAll();
		if (!list.isEmpty())
			return list;
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
			f.get().setSeatPrice(flight.getSeatPrice());
			f.get().setTotalSeat(flight.getTotalSeat());
			return flightRepository.save(f.get());
		} else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");

	}

	@Override
	public Flight resetStatus(long id) {
		Optional<Flight> b = flightRepository.findById(id);
		if (b.isPresent()) {
			Map<String, Integer> map = b.get().getFlightBookingStatus().getBookingStatus();
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				entry.setValue(0);
			}
			b.get().getFlightBookingStatus().setBookingStatus(map);
			return flightRepository.save(b.get());
		} else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");

	}

	@Override
	public List<List<String>> getAllCityNames() {
		List<Flight> list=flightRepository.findAll();
		if (!list.isEmpty()) {
			List<String> origins = list.stream().map(Flight::getOrigin).distinct().collect(Collectors.toList());
            List<String> destinations = list.stream().map(Flight::getDestination).distinct().collect(Collectors.toList());
            List<List<String>> cityNames = new ArrayList<>();
            cityNames.add(origins);
            cityNames.add(destinations);
            return cityNames;
		}
		else
			throw new FlightDetailsNotFoundException("Flight details are not found");

	}

}
