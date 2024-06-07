package com.flightservice.service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightservice.entity.Flight;
import com.flightservice.entity.FlightBookingDetails;
import com.flightservice.entity.FlightSeats;
import com.flightservice.exception.FlightDetailsNotFoundException;
import com.flightservice.repository.FlightBookingDetailsRepository;
import com.flightservice.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private FlightBookingDetailsRepository flightBookingDetailsRepository;

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
		flight.setNextDay(isArrivalNextDay(flight.getDepartureTime(), flight.getArrivalTime()) ? "+1 DAY" : "");
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
			f.get().setDuration(flight.getDuration());
			String[] parts = flight.getDuration().split(":");
			long hours = Long.parseLong(parts[0]);
			long minutes = Long.parseLong(parts[1]);
			f.get().setArrivalTime(flight.getDepartureTime().plus(Duration.ofHours(hours).plusMinutes(minutes)));
			f.get().setNextDay(isArrivalNextDay(flight.getDepartureTime(), flight.getArrivalTime()) ? "+1 DAY" : "");
			f.get().setDestination(flight.getDestination());
			f.get().setOrigin(flight.getOrigin());
			f.get().setAirlineLogo(flight.getAirlineLogo());
			f.get().setFlightModel(flight.getFlightModel());
			f.get().setSeatPrice(flight.getSeatPrice());
			f.get().setTotalSeat(flight.getTotalSeat());
			f.get().setStopOver(flight.getStopOver());
			return flightRepository.save(f.get());
		} else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");

	}

	@Override
	public Flight resetStatus(long id) {
		Optional<Flight> b = flightRepository.findById(id);
		if (b.isPresent()) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (Map.Entry<String, Integer> m : b.get().getFlightBookingStatus().entrySet()) {
				map.put(m.getKey(), 0);
			}
			b.get().setFlightBookingStatus(map);
			return flightRepository.save(b.get());
		} else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");

	}

	@Override
	public List<List<String>> getAllCityNames() {
		List<Flight> list = flightRepository.findAll();
		if (!list.isEmpty()) {
			List<String> origins = list.stream().map(Flight::getOrigin).distinct().collect(Collectors.toList());
			List<String> destinations = list.stream().map(Flight::getDestination).distinct()
					.collect(Collectors.toList());
			List<List<String>> cityNames = new ArrayList<>();
			cityNames.add(origins);
			cityNames.add(destinations);
			return cityNames;
		} else
			throw new FlightDetailsNotFoundException("Flight details are not found");

	}

	@Override
	public List<Flight> getAllAvailableFlights(String from, String to, Date departure, String travellerClass) {
		List<Flight> list = flightRepository.findAll();
		if (!list.isEmpty()) {
			List<Flight> flightList = list.stream().filter(f -> f.getOrigin().equalsIgnoreCase(from))
					.filter(f -> f.getDestination().equalsIgnoreCase(to))
					.filter(f -> f.getFlightBookingStatus().get(travellerClass) > 0).collect(Collectors.toList());

			List<Flight> flights = new ArrayList<Flight>();
			for (Flight f : flightList) {
				String[] s = f.getDepartureTime().toString().split("T");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				if (s[0].equalsIgnoreCase(formatter.format(departure))) {
					flights.add(f);
				}
			}
			return flights;
		} else
			throw new FlightDetailsNotFoundException("Flight details are not found");

	}

	public static boolean isArrivalNextDay(LocalDateTime departureTime, LocalDateTime arrivalTime) {
		LocalDateTime nextDayOfDeparture = departureTime.plusDays(1).truncatedTo(ChronoUnit.DAYS);
		LocalDateTime arrivalDate = arrivalTime.truncatedTo(ChronoUnit.DAYS);

		return arrivalDate.equals(nextDayOfDeparture);
	}

	@Override
	public FlightBookingDetails bookFlight(FlightBookingDetails flightBookingDetails) {
		long MIN_id = 100000;
		int count = flightBookingDetailsRepository.findAll().size();
		flightBookingDetails.setBookingId(count == 0 ? MIN_id : MIN_id + count);
		return flightBookingDetailsRepository.save(flightBookingDetails);
	}

	@Override
	public Flight addSeats(long id) {
		int FIRST_CLASS_ROWS = 4;
		int FIRST_CLASS_COLUMNS = 4;
		double FIRST_CLASS_PRICE = 0;
		String FIRST_CLASS_TYPE = "firstclass";

		int BUSINESS_ROWS = 8;
		int BUSINESS_COLUMNS = 6;
		double BUSINESS_PRICE = 0;
		String BUSINESS_TYPE = "business";
		
		int ECONOMY_ROWS = 17;
		int ECONOMY_COLUMNS = 6;
		double ECONOMY_PRICE = 0;
		String ECONOMY_TYPE = "economy";

		Optional<Flight> flight = flightRepository.findById(id);
		if (!flight.isEmpty()) {
			
         Map<String,Double> map=flight.get().getSeatPrice();
         
         FIRST_CLASS_PRICE= map.get(FIRST_CLASS_TYPE);
         BUSINESS_PRICE= map.get(BUSINESS_TYPE);
         ECONOMY_PRICE= map.get(ECONOMY_TYPE);
         
         List<FlightSeats> seats = new ArrayList<>();
         
         for (int i = 1; i <= FIRST_CLASS_ROWS; i++) {
             for (int j = 1; j <= FIRST_CLASS_COLUMNS; j++) {
                 FlightSeats seat = new FlightSeats(0, "F" + i + j, FIRST_CLASS_TYPE, FIRST_CLASS_PRICE, true);
                 seats.add(seat);
             }
         }
         
         for (int i = 1; i <= BUSINESS_ROWS; i++) {
             for (int j = 1; j <= BUSINESS_COLUMNS; j++) {
            	 FlightSeats seat = new FlightSeats(0, "B" + i + j, BUSINESS_TYPE, BUSINESS_PRICE, true);
                 seats.add(seat);
             }
         }
         
         for (int i = 1; i <= ECONOMY_ROWS; i++) {
             for (int j = 1; j <= ECONOMY_COLUMNS; j++) {
            	 FlightSeats seat = new FlightSeats(0, "E" + i + j, "Economy", ECONOMY_PRICE, true);
                 seats.add(seat);
             }
         }

         flight.get().setFlightSeats(seats);
         
         return flightRepository.save(flight.get());
		}else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");

	}
}
