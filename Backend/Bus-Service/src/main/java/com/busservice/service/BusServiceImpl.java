package com.busservice.service;

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

import com.busservice.entity.Bus;
import com.busservice.entity.BusBookingDetails;
import com.busservice.entity.BusPassenger;
import com.busservice.entity.BusSeats;
import com.busservice.entity.BusTravellerBusSeats;
import com.busservice.exception.BusDetailsNotFoundException;
import com.busservice.externalclass.Registration;
import com.busservice.externalclass.Traveller;
import com.busservice.proxy.UserProxy;
import com.busservice.repository.BusBookingDetailsRepository;
import com.busservice.repository.BusRepository;
import com.busservice.repository.BusSeatsRepository;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private BusSeatsRepository busSeatsRepository;

	@Autowired
	private BusBookingDetailsRepository busBookingDetailsRepository;

	@Autowired
	UserProxy uproxy;

	@Override
	public List<Bus> getAllBus() {
		if (!busRepository.findAll().isEmpty())
			return busRepository.findAll();
		else
			throw new BusDetailsNotFoundException("Bus details are not found");
	}

	@Override
	public Optional<Bus> getBusById(long id) {
		Optional<Bus> Bus = busRepository.findById(id);
		if (!Bus.isEmpty())
			return Bus;
		else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");
	}

	@Override
	public Bus saveBus(Bus bus) {
		long MIN_id = 100000;
		int count = busRepository.findAll().size();
		bus.setBusId(count == 0 ? MIN_id : MIN_id + count);
		String[] parts = bus.getDuration().split(":");
		long hours = Long.parseLong(parts[0]);
		long minutes = Long.parseLong(parts[1]);
		bus.setArrivalTime(bus.getDepartureTime().plus(Duration.ofHours(hours).plusMinutes(minutes)));
		bus.setNextDay(isArrivalNextDay(bus.getDepartureTime(), bus.getArrivalTime()) ? "+1 DAY" : "");
		return busRepository.save(bus);
	}

	@Override
	public String deleteBus(long id) {
		Optional<Bus> Bus = busRepository.findById(id);
		if (!Bus.isEmpty()) {
			busRepository.deleteById(id);
			return "Bus details are deleted";
		} else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");

	}

	@Override
	public Bus updateBus(long id, Bus bus) {
		Optional<Bus> b = busRepository.findById(id);
		if (!b.isEmpty()) {
			b.get().setBusCompany(bus.getBusCompany());
			b.get().setDuration(bus.getDuration());
			String[] parts = bus.getDuration().split(":");
			long hours = Long.parseLong(parts[0]);
			long minutes = Long.parseLong(parts[1]);
			b.get().setArrivalTime(bus.getDepartureTime().plus(Duration.ofHours(hours).plusMinutes(minutes)));
			b.get().setDepartureTime(bus.getDepartureTime());
			b.get().setNextDay(isArrivalNextDay(bus.getDepartureTime(), bus.getArrivalTime()) ? "+1 DAY" : "");
			b.get().setDestination(bus.getDestination());
			b.get().setOrigin(bus.getOrigin());
			b.get().setBusModel(bus.getBusModel());
			b.get().setSeatPrice(bus.getSeatPrice());
			b.get().setTotalSeat(bus.getTotalSeat());
			b.get().setRoute(bus.getRoute());
			return busRepository.save(b.get());
		} else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");

	}

	@Override
	public BusBookingDetails resetStatus(long id) {
		Optional<BusBookingDetails> fbd = busBookingDetailsRepository.findById(id);
		if (fbd.isPresent()) {
			fbd.get().setPaymentStatus("Payment Cancelled & Refunded");
			return busBookingDetailsRepository.save(fbd.get());
		} else
			throw new BusDetailsNotFoundException("Bus details of bus id: " + id + " are not found");

	}

	@Override
	public List<List<String>> getAllCityNames() {
		List<Bus> list = busRepository.findAll();
		if (!list.isEmpty()) {
			List<String> origins = list.stream().map(Bus::getOrigin).distinct().collect(Collectors.toList());
			List<String> destinations = list.stream().map(Bus::getDestination).distinct().collect(Collectors.toList());
			List<List<String>> cityNames = new ArrayList<>();
			cityNames.add(origins);
			cityNames.add(destinations);
			return cityNames;
		} else
			throw new BusDetailsNotFoundException("Bus details are not found");

	}

	@Override
	public List<Bus> getAllAvailableBuses(String from, String to, Date departure) {
		List<Bus> list = busRepository.findAll();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (!list.isEmpty()) {
			List<Bus> busList = list.stream().filter(bus -> bus.getOrigin().equalsIgnoreCase(from))
					.filter(bus -> bus.getDestination().equalsIgnoreCase(to))
					.filter(bus -> bus.getBusBookingStatus().values().stream().mapToInt(Integer::intValue).sum() > 0)
					.filter(bus -> bus.getDepartureTime().toString().split("T")[0]
							.equalsIgnoreCase(formatter.format(departure)))
					.collect(Collectors.toList());

			return busList;
		} else
			throw new BusDetailsNotFoundException("bus details are not found");

	}

	public static boolean isArrivalNextDay(LocalDateTime departureTime, LocalDateTime arrivalTime) {
		LocalDateTime nextDayOfDeparture = departureTime.plusDays(1).truncatedTo(ChronoUnit.DAYS);
		LocalDateTime arrivalDate = arrivalTime.truncatedTo(ChronoUnit.DAYS);

		return arrivalDate.equals(nextDayOfDeparture);
	}

	@Override
	public BusBookingDetails bookBus(long id, BusTravellerBusSeats ftfs, String username) {
		Optional<Bus> busOptional = busRepository.findById(id);
		if (busOptional.isEmpty()) {
			throw new BusDetailsNotFoundException("Bus details of bus id: " + id + " are not found");
		}
		Map<String, Integer> map = busOptional.get().getBusBookingStatus();
		List<Traveller> travellers = ftfs.getTravellers();
		List<BusSeats> busSeats = ftfs.getBusSeats();
		Bus bus = busOptional.get();

		if (travellers.size() != busSeats.size()) {
			throw new IllegalArgumentException("The number of travellers must match the number of bus seats.");
		}

		long MIN_ID = 100000;
		int count = busBookingDetailsRepository.findAll().size();

		BusBookingDetails bookingDetails = new BusBookingDetails();
		bookingDetails.setBusBookingId(count == 0 ? MIN_ID : MIN_ID + count);
		bookingDetails.setBusCompany(bus.getBusCompany());
		bookingDetails.setBusModel(bus.getBusModel());
		bookingDetails.setOrigin(bus.getOrigin());
		bookingDetails.setDestination(bus.getDestination());
		bookingDetails.setDepartureTime(bus.getDepartureTime());
		bookingDetails.setArrivalTime(bus.getArrivalTime());
		bookingDetails.setDuration(bus.getDuration());
		bookingDetails.setRoute(bus.getRoute());
		bookingDetails.setNextDay(bus.getNextDay());
		bookingDetails.setUsername(username);
		bookingDetails.setPaymentStatus("Payment has to be done");
		Registration user = uproxy.showUserByUserName(username).getBody();
		bookingDetails.setName(user.getName());
		bookingDetails.setEmail(user.getEmail());
		bookingDetails.setPhonenumber(user.getMobile());
		bookingDetails.setUsername(username);
		bookingDetails.setBookedDate(LocalDateTime.now());
		double totalPrice = 0;
		List<BusPassenger> busPassengerList = new ArrayList<BusPassenger>();
		for (int i = 0; i < travellers.size(); i++) {
			Traveller traveller = travellers.get(i);
			BusSeats seat = busSeats.get(i);
			BusPassenger passenger = new BusPassenger();
			passenger.setAddress(traveller.getAddress());
			passenger.setGender(traveller.getGender());
			passenger.setMobile(traveller.getMobile());
			passenger.setAge(traveller.getAge());
			passenger.setName(traveller.getName());
			passenger.setSeatClass(seat.getSeatType());
			passenger.setSeatNo(seat.getSeatNo());
			busPassengerList.add(passenger);
			totalPrice += seat.getSeatPrice();
			BusSeats fs = busSeatsRepository.findById(seat.getSeatId()).get();
			fs.setBookingStatus(false);
			busSeatsRepository.save(fs);

			for (Map.Entry<String, Integer> me : map.entrySet()) {
				if (me.getKey().equalsIgnoreCase(seat.getSeatType())) {
					map.put(seat.getSeatType(), me.getValue() - 1);
				}
			}
		}
		busOptional.get().setBusBookingStatus(map);
		busRepository.save(busOptional.get());
		bookingDetails.setTotalPrice(totalPrice);
		bookingDetails.setBusPassenger(busPassengerList);

		return busBookingDetailsRepository.save(bookingDetails);

	}

	@Override
	public Bus addSeats(long id) {
		int LOWER_SLEEPER_ROWS = 6;
		int LOWER_SLEEPER_COLUMNS = 1;
		double LOWER_SLEEPER_PRICE = 0;
		String LOWER_SLEEPER_TYPE = "lowerSleeper";

		int LOWER_SEAT_ROWS = 12;
		int LOWER_SEAT_COLUMNS = 2;
		double LOWER_SEAT_PRICE = 0;
		String LOWER_SEAT_TYPE = "lowerSeat";

		int UPPER_SLEEPER_ROWS = 6;
		int UPPER_SLEEPER_COLUMNS = 1;
		double UPPER_SLEEPER_PRICE = 0;
		String UPPER_SLEEPER_TYPE = "upperSleeper";

		int UPPER_DOUBLE_SLEEPER_ROWS = 6;
		int UPPER_DOUBLE_SLEEPER_COLUMNS = 2;
		double UPPER_DOUBLE_SLEEPER_PRICE = 0;
		String UPPER_DOUBLE_SLEEPER_TYPE = "upperDoubleSleeper";

		Optional<Bus> bus = busRepository.findById(id);
		if (bus.isPresent()) {

			Map<String, Double> map = bus.get().getSeatPrice();

			LOWER_SLEEPER_PRICE = map.get(LOWER_SLEEPER_TYPE);
			LOWER_SEAT_PRICE = map.get(LOWER_SEAT_TYPE);
			UPPER_SLEEPER_PRICE = map.get(UPPER_SLEEPER_TYPE);
			UPPER_DOUBLE_SLEEPER_PRICE = map.get(UPPER_DOUBLE_SLEEPER_TYPE);

			List<BusSeats> seats = new ArrayList<>();

			for (int i = 1; i <= LOWER_SLEEPER_ROWS; i++) {
				for (int j = 1; j <= LOWER_SLEEPER_COLUMNS; j++) {
					BusSeats seat = new BusSeats(0, "LS" + i + j, LOWER_SLEEPER_TYPE, LOWER_SLEEPER_PRICE, true);
					seats.add(seat);
				}
			}

			for (int i = 1; i <= LOWER_SEAT_ROWS; i++) {
				for (int j = 1; j <= LOWER_SEAT_COLUMNS; j++) {
					BusSeats seat = new BusSeats(0, "L" + i + j, LOWER_SEAT_TYPE, LOWER_SEAT_PRICE, true);
					seats.add(seat);
				}
			}

			for (int i = 1; i <= UPPER_SLEEPER_ROWS; i++) {
				for (int j = 1; j <= UPPER_SLEEPER_COLUMNS; j++) {
					BusSeats seat = new BusSeats(0, "US" + i + j, UPPER_SLEEPER_TYPE, UPPER_SLEEPER_PRICE, true);
					seats.add(seat);
				}
			}

			for (int i = 1; i <= UPPER_DOUBLE_SLEEPER_ROWS; i++) {
				for (int j = 1; j <= UPPER_DOUBLE_SLEEPER_COLUMNS; j++) {
					BusSeats seat = new BusSeats(0, "UD" + i + j, UPPER_DOUBLE_SLEEPER_TYPE, UPPER_DOUBLE_SLEEPER_PRICE,
							true);
					seats.add(seat);
				}
			}

			bus.get().setBusSeats(seats);

			return busRepository.save(bus.get());
		} else
			throw new BusDetailsNotFoundException("Bus details of bus id: " + id + " are not found");
	}

	@Override
	public BusBookingDetails paymentstatuschange(long bookingid) {
		Optional<BusBookingDetails> fbd = busBookingDetailsRepository.findById(bookingid);
		if (fbd.isPresent()) {
			fbd.get().setPaymentStatus("Payment done");
			return busBookingDetailsRepository.save(fbd.get());
		} else
			throw new BusDetailsNotFoundException("Bus details of bus id: " + bookingid + " are not found");

	}

	@Override
	public BusBookingDetails getBusBookingDetailsById(long id) {
		Optional<BusBookingDetails> busBookingDetails = busBookingDetailsRepository.findById(id);
		if (!busBookingDetails.isEmpty())
			return busBookingDetails.get();
		else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");

	}

	@Override
	public List<BusBookingDetails> getBusBookingDetailsByUsername(String username) {
		List<BusBookingDetails> busBookingDetails = busBookingDetailsRepository.findAll().stream()
				.filter(b -> b.getUsername().equalsIgnoreCase(username))
				.sorted((b1, b2) -> b2.getBookedDate().compareTo(b1.getBookedDate())).collect(Collectors.toList());
		if (!busBookingDetails.isEmpty())
			return busBookingDetails;
		else
			throw new BusDetailsNotFoundException("Bus details of Username: " + username + " are not found");

	}

}
