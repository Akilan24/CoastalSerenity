package com.flightservice.service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightservice.entity.Flight;
import com.flightservice.entity.FlightBookingDetails;
import com.flightservice.entity.FlightPassenger;
import com.flightservice.entity.FlightSeats;
import com.flightservice.entity.FlightTravellerFlightSeats;
import com.flightservice.exception.FlightDetailsNotFoundException;
import com.flightservice.externalclass.Registration;
import com.flightservice.externalclass.Traveller;
import com.flightservice.proxy.UserProxy;
import com.flightservice.repository.FlightBookingDetailsRepository;
import com.flightservice.repository.FlightRepository;
import com.flightservice.repository.FlightSeatsRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private FlightSeatsRepository flightSeatsRepository;

	@Autowired
	private FlightBookingDetailsRepository flightBookingDetailsRepository;

	@Autowired
	UserProxy uproxy;

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
			f.get().setDepartureTime(flight.getDepartureTime());
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
	public FlightBookingDetails resetStatus(long id) {
		Optional<FlightBookingDetails> fbd = flightBookingDetailsRepository.findById(id);
		if (fbd.isPresent()) {
			fbd.get().setPaymentStatus("Payment Cancelled & Refunded");
			return flightBookingDetailsRepository.save(fbd.get());
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
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			List<Flight> flightList = list.stream().filter(f -> f.getOrigin().equalsIgnoreCase(from))
					.filter(f -> f.getDestination().equalsIgnoreCase(to))
					.filter(f -> f.getFlightBookingStatus().get(travellerClass) > 0)
					.filter(bus -> bus.getDepartureTime().toString().split("T")[0]
							.equalsIgnoreCase(formatter.format(departure)))
					.collect(Collectors.toList());

			return flightList;
		} else
			throw new FlightDetailsNotFoundException("Flight details are not found");

	}

	public static boolean isArrivalNextDay(LocalDateTime departureTime, LocalDateTime arrivalTime) {
		LocalDateTime nextDayOfDeparture = departureTime.plusDays(1).truncatedTo(ChronoUnit.DAYS);
		LocalDateTime arrivalDate = arrivalTime.truncatedTo(ChronoUnit.DAYS);

		return arrivalDate.equals(nextDayOfDeparture);
	}

	@Override
	public FlightBookingDetails bookFlight(long id, FlightTravellerFlightSeats ftfs, String username) {
		Optional<Flight> flightOptional = flightRepository.findById(id);
		if (flightOptional.isEmpty()) {
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");
		}
		Map<String, Integer> map = flightOptional.get().getFlightBookingStatus();
		List<Traveller> travellers = ftfs.getTravellers();
		List<FlightSeats> flightSeats = ftfs.getFlightSeats();
		Flight flight = flightOptional.get();

		if (travellers.size() != flightSeats.size()) {
			throw new IllegalArgumentException("The number of travellers must match the number of flight seats.");
		}

		long MIN_ID = 100000;
		int count = flightBookingDetailsRepository.findAll().size();

		FlightBookingDetails bookingDetails = new FlightBookingDetails();
		bookingDetails.setFlightBookingId(count == 0 ? MIN_ID : MIN_ID + count);
		bookingDetails.setAirline(flight.getAirline());
		bookingDetails.setAirlineLogo(flight.getAirlineLogo());
		bookingDetails.setFlightModel(flight.getFlightModel());
		bookingDetails.setOrigin(flight.getOrigin());
		bookingDetails.setDestination(flight.getDestination());
		bookingDetails.setDepartureTime(flight.getDepartureTime());
		bookingDetails.setArrivalTime(flight.getArrivalTime());
		bookingDetails.setDuration(flight.getDuration());
		bookingDetails.setStopOver(flight.getStopOver());
		bookingDetails.setNextDay(flight.getNextDay());
		bookingDetails.setUsername(username);
		bookingDetails.setPaymentStatus("Payment has to be done");
		Registration user = uproxy.showUserByUserName(username).getBody();
		bookingDetails.setName(user.getName());
		bookingDetails.setEmail(user.getEmail());
		bookingDetails.setPhonenumber(user.getMobile());
		bookingDetails.setUsername(username);

		double totalPrice = 0;
		List<FlightPassenger> flightPassengerList = new ArrayList<FlightPassenger>();
		for (int i = 0; i < travellers.size(); i++) {
			Traveller traveller = travellers.get(i);
			FlightSeats seat = flightSeats.get(i);
			FlightPassenger passenger = new FlightPassenger();
			passenger.setAddress(traveller.getAddress());
			passenger.setGender(traveller.getGender());
			passenger.setMobile(traveller.getMobile());
			passenger.setAge(traveller.getAge());
			passenger.setName(traveller.getName());
			passenger.setSeatClass(seat.getSeatClass());
			passenger.setSeatNo(seat.getSeatNumber());
			flightPassengerList.add(passenger);
			totalPrice += seat.getPrice();
			FlightSeats fs = flightSeatsRepository.findById(seat.getId()).get();
			fs.setAvailable(false);
			flightSeatsRepository.save(fs);

			for (Map.Entry<String, Integer> me : map.entrySet()) {
				if (me.getKey().equalsIgnoreCase(seat.getSeatClass())) {
					map.put(seat.getSeatClass(), me.getValue() - 1);
				}
			}
		}
		flightOptional.get().setFlightBookingStatus(map);
		flightRepository.save(flightOptional.get());
		bookingDetails.setTotalPrice(totalPrice);
		bookingDetails.setFlightPassenger(flightPassengerList);
        bookingDetails.setBookedDate(LocalTimeDate.now());
		return flightBookingDetailsRepository.save(bookingDetails);

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

			Map<String, Double> map = flight.get().getSeatPrice();

			FIRST_CLASS_PRICE = map.get(FIRST_CLASS_TYPE);
			BUSINESS_PRICE = map.get(BUSINESS_TYPE);
			ECONOMY_PRICE = map.get(ECONOMY_TYPE);

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
					FlightSeats seat = new FlightSeats(0, "E" + i + j, ECONOMY_TYPE, ECONOMY_PRICE, true);
					seats.add(seat);
				}
			}

			flight.get().setFlightSeats(seats);

			return flightRepository.save(flight.get());
		} else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");

	}

	@Override
	public FlightBookingDetails paymentstatuschange(long bookingid) {
		Optional<FlightBookingDetails> fbd = flightBookingDetailsRepository.findById(bookingid);
		if (fbd.isPresent()) {
			fbd.get().setPaymentStatus("Payment done");
			return flightBookingDetailsRepository.save(fbd.get());
		} else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + bookingid + " are not found");

	}

	@Override
	public FlightBookingDetails getFlightBookingDetailsById(long id) {
		Optional<FlightBookingDetails> flightBookingDetails = flightBookingDetailsRepository.findById(id);
		if (!flightBookingDetails.isEmpty())
			return flightBookingDetails.get();
		else
			throw new FlightDetailsNotFoundException("Flight details of flight id: " + id + " are not found");

	}

	@Override
	public List<FlightBookingDetails> getFlightBookingDetailsByUsername(String username) {
		List<FlightBookingDetails> flightBookingDetails = flightBookingDetailsRepository.findAll().stream()
				.filter(f -> f.getUsername().equalsIgnoreCase(username))
				.sorted((f1, f2) -> f2.getBookedDate().compareTo(f1.getBookedDate())).collect(Collectors.toList());
		if (!flightBookingDetails.isEmpty())
			return flightBookingDetails;
		else
			throw new FlightDetailsNotFoundException("Flight details of Username: " + username + " are not found");

	}

}
