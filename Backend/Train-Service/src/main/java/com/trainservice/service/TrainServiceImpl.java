package com.trainservice.service;

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

import com.trainservice.entity.Train;
import com.trainservice.entity.TrainBookingDetails;
import com.trainservice.entity.TrainPassenger;
import com.trainservice.entity.TrainSeats;
import com.trainservice.exception.TrainDetailsNotFoundException;
import com.trainservice.externalclass.Registration;
import com.trainservice.externalclass.Traveller;
import com.trainservice.proxy.UserProxy;
import com.trainservice.repository.TrainBookingDetailsRepository;
import com.trainservice.repository.TrainRepository;
import com.trainservice.repository.TrainSeatsRepository;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private TrainBookingDetailsRepository trainBookingDetailsRepository;

	@Autowired
	UserProxy uproxy;

	@Override
	public List<Train> getAllTrain() {
		if (!trainRepository.findAll().isEmpty())
			return trainRepository.findAll();
		else
			throw new TrainDetailsNotFoundException("Train details are not found");
	}

	@Override
	public Optional<Train> getTrainByTrainId(long id) {
		Optional<Train> Train = trainRepository.findById(id);
		if (!Train.isEmpty())
			return Train;
		else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");
	}

	@Override
	public Train saveTrain(Train Train) {
		long MIN_id = 100000;
		int count = trainRepository.findAll().size();
		Train.setTrainId(count == 0 ? MIN_id : MIN_id + count);
		String[] parts = Train.getDuration().split(":");
		long hours = Long.parseLong(parts[0]);
		long minutes = Long.parseLong(parts[1]);
		Train.setArrivalTime(Train.getDepartureTime().plus(Duration.ofHours(hours).plusMinutes(minutes)));
		Train.setNextDay(isArrivalNextDay(Train.getDepartureTime(), Train.getArrivalTime()) ? "+1 DAY" : "");
		return trainRepository.save(Train);
	}

	@Override
	public String deleteTrainByTrainId(long id) {
		Optional<Train> Train = trainRepository.findById(id);
		if (!Train.isEmpty()) {
			trainRepository.deleteById(id);
			return "Train details are deleted";
		} else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");

	}

	@Override
	public Train updateTrainByTrainId(long id, Train Train) {
		Optional<Train> b = trainRepository.findById(id);
		if (!b.isEmpty()) {
			b.get().setPnr(Train.getPnr());
			b.get().setDuration(Train.getDuration());
			String[] parts = Train.getDuration().split(":");
			long hours = Long.parseLong(parts[0]);
			long minutes = Long.parseLong(parts[1]);
			b.get().setArrivalTime(Train.getDepartureTime().plus(Duration.ofHours(hours).plusMinutes(minutes)));
			b.get().setDepartureTime(Train.getDepartureTime());
			b.get().setNextDay(isArrivalNextDay(Train.getDepartureTime(), Train.getArrivalTime()) ? "+1 DAY" : "");
			b.get().setDestination(Train.getDestination());
			b.get().setOrigin(Train.getOrigin());
			b.get().setTrainName(Train.getTrainName());
			b.get().setSeatPrice(Train.getSeatPrice());
			b.get().setTotalSeat(Train.getTotalSeat());
			return trainRepository.save(b.get());
		} else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");

	}

	@Override
	public TrainBookingDetails cancelPaymentByTrainBookingId(long id) {
		Optional<TrainBookingDetails> fbd = trainBookingDetailsRepository.findById(id);
		if (fbd.isPresent()) {
			fbd.get().setPaymentStatus("Payment Cancelled & Refunded");
			return trainBookingDetailsRepository.save(fbd.get());
		} else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");

	}

	@Override
	public List<List<String>> getAllCityNames() {
		List<Train> list = trainRepository.findAll();
		if (!list.isEmpty()) {
			List<String> origins = list.stream().map(Train::getOrigin).distinct().collect(Collectors.toList());
			List<String> destinations = list.stream().map(Train::getDestination).distinct()
					.collect(Collectors.toList());
			List<List<String>> cityNames = new ArrayList<>();
			cityNames.add(origins);
			cityNames.add(destinations);
			return cityNames;
		} else
			throw new TrainDetailsNotFoundException("Train details are not found");

	}

	@Override
	public List<Train> getAllAvailableTrains(String from, String to, Date departure) {
		List<Train> list = trainRepository.findAll();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (!list.isEmpty()) {
			List<Train> TrainList = list.stream().filter(Train -> Train.getOrigin().equalsIgnoreCase(from))
					.filter(Train -> Train.getDestination().equalsIgnoreCase(to))
					.filter(Train -> Train.getDepartureTime().toString().split("T")[0]
							.equalsIgnoreCase(formatter.format(departure)))
					.collect(Collectors.toList());

			return TrainList;
		} else
			throw new TrainDetailsNotFoundException("Train details are not found");

	}

	public static boolean isArrivalNextDay(LocalDateTime departureTime, LocalDateTime arrivalTime) {
		LocalDateTime nextDayOfDeparture = departureTime.plusDays(1).truncatedTo(ChronoUnit.DAYS);
		LocalDateTime arrivalDate = arrivalTime.truncatedTo(ChronoUnit.DAYS);

		return arrivalDate.equals(nextDayOfDeparture);
	}

	@Override
	public TrainBookingDetails bookTrain(long id, List<Traveller> travellers, String seatType, String boardingStation,
			String username) {
		Optional<Train> TrainOptional = trainRepository.findById(id);
		if (TrainOptional.isEmpty()) {
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");
		}
		Map<String, Integer> map = TrainOptional.get().getTrainBookingStatus();
		Train Train = TrainOptional.get();

		if (travellers.size() != travellers.size()) {
			throw new IllegalArgumentException("The number of travellers must match the number of Train seats.");
		}

		long MIN_ID = 100000;
		int count = trainBookingDetailsRepository.findAll().size();

		TrainBookingDetails bookingDetails = new TrainBookingDetails();
		bookingDetails.setTrainBookingId(count == 0 ? MIN_ID : MIN_ID + count);
		bookingDetails.setTrainName(Train.getTrainName());
		bookingDetails.setPnr(Train.getPnr());
		bookingDetails.setOrigin(Train.getOrigin());
		bookingDetails.setDestination(Train.getDestination());
		bookingDetails.setDepartureTime(Train.getDepartureTime());
		bookingDetails.setArrivalTime(Train.getArrivalTime());
		bookingDetails.setDuration(Train.getDuration());
		bookingDetails.setNextDay(Train.getNextDay());
		bookingDetails.setUsername(username);
		bookingDetails.setPaymentStatus("Payment has to be done");
		Registration user = uproxy.showUserByUserName(username);
		bookingDetails.setName(user.getName());
		bookingDetails.setEmail(user.getEmail());
		bookingDetails.setPhonenumber(user.getMobile());
		bookingDetails.setUsername(username);
		bookingDetails.setBookedDate(LocalDateTime.now());
		bookingDetails.setBoardingStation(boardingStation);
		switch (seatType) {
		case "SL":
			bookingDetails.setSeatType("SL - Sleeper");
			break;
		case "1A":
			bookingDetails.setSeatType("1A - 1st Class AC");
			break;
		case "2A":
			bookingDetails.setSeatType("2A - 2 Tier AC");
			break;
		case "3A":
			bookingDetails.setSeatType("3A - 3 Tier AC");
			break;
		case "CC":
			bookingDetails.setSeatType("CC - CC Chair Car");
			break;
		}

		double totalPrice = 0;
		for (Map.Entry<String, Double> me : Train.getSeatPrice().entrySet()) {
			if (me.getKey().equalsIgnoreCase(seatType)) {
				totalPrice = me.getValue() * travellers.size();
			}
		}
		bookingDetails.setTotalPrice(totalPrice);
		List<TrainPassenger> TrainPassengerList = new ArrayList<TrainPassenger>();
		for (int i = 0; i < travellers.size(); i++) {
			Traveller traveller = travellers.get(i);
			TrainPassenger passenger = new TrainPassenger();
			passenger.setAddress(traveller.getAddress());
			passenger.setGender(traveller.getGender());
			passenger.setMobile(traveller.getMobile());
			passenger.setAge(traveller.getAge());
			passenger.setSeatType(seatType);
			passenger.setCoachNo("NA");
			for (Map.Entry<String, Integer> me : Train.getTrainBookingStatus().entrySet()) {
				if (me.getKey().equalsIgnoreCase(seatType)) {
					if (me.getValue() > 0) {
						passenger.setSeatNo("Seat Confirmed");
						map.put(seatType, me.getValue() - 1);
						map.entrySet().stream().forEach(m->m.getValue());
					} else {
						for (Map.Entry<String, Integer> me1 : Train.getTrainBookingStatus().entrySet()) {
							if (me1.getKey().equalsIgnoreCase(seatType)) {
								int no = me1.getValue() + 1;
								passenger.setSeatNo("WL" + no);
								Train.getTrainWaitingListStatus().put(seatType, no);
							}
						}

					}
				}
			}
			passenger.setName(traveller.getName());
			TrainPassengerList.add(passenger);

		}
		Train.setTrainBookingStatus(map);
		trainRepository.save(Train);
		bookingDetails.setBookedDate(LocalDateTime.now());
		bookingDetails.setTrainPassenger(TrainPassengerList);

		return trainBookingDetailsRepository.save(bookingDetails);

	}

	@Override
	public Train addSeatsByTrainId(long id) {

		int TOTAL_COACH_1A = 1;
		int TOTAL_SEAT_PER_COACH_1A = 18;
		double COACH_PRICE_1A = 0;
		String COACH_TYPE_1A = "1A";

		int TOTAL_COACH_2A = 2;
		int TOTAL_SEAT_PER_COACH_2A = 48;
		double COACH_PRICE_2A = 0;
		String COACH_TYPE_2A = "2A";

		int TOTAL_COACH_3A = 3;
		int TOTAL_SEAT_PER_COACH_3A = 64;
		double COACH_PRICE_3A = 0;
		String COACH_TYPE_3A = "3A";

		int TOTAL_COACH_SL = 6;
		int TOTAL_SEAT_PER_COACH_SL = 72;
		double COACH_PRICE_SL = 0;
		String COACH_TYPE_SL = "SL";

		int TOTAL_COACH_CC = 2;
		int TOTAL_SEAT_PER_COACH_CC = 78;
		double COACH_PRICE_CC = 0;
		String COACH_TYPE_CC = "CC";

		Optional<Train> Train = trainRepository.findById(id);
		if (Train.isPresent()) {

			Map<String, Double> map = Train.get().getSeatPrice();

			COACH_PRICE_1A = map.get(COACH_TYPE_1A);
			COACH_PRICE_2A = map.get(COACH_TYPE_2A);
			COACH_PRICE_3A = map.get(COACH_TYPE_3A);
			COACH_PRICE_SL = map.get(COACH_TYPE_SL);
			COACH_PRICE_CC = map.get(COACH_TYPE_CC);

			List<TrainSeats> seats = new ArrayList<>();
			for (int s = 1; s <= TOTAL_COACH_1A; s++) {
				for (int i = 1; i <= TOTAL_SEAT_PER_COACH_1A; i++) {
					TrainSeats seat = new TrainSeats(0, "CS" + i, COACH_TYPE_1A, COACH_PRICE_1A, "S" + s, true);
					seats.add(seat);
				}
			}
			for (int s = TOTAL_COACH_1A + 1; s <= TOTAL_COACH_1A + TOTAL_COACH_2A; s++) {
				for (int i = 1; i <= TOTAL_SEAT_PER_COACH_2A; i++) {
					TrainSeats seat = new TrainSeats(0, "CS" + i, COACH_TYPE_2A, COACH_PRICE_2A, "S" + s, true);
					seats.add(seat);
				}
			}
			for (int s = TOTAL_COACH_1A + TOTAL_COACH_2A + 1; s <= TOTAL_COACH_1A + TOTAL_COACH_2A
					+ TOTAL_COACH_3A; s++) {
				for (int i = 1; i <= TOTAL_SEAT_PER_COACH_3A; i++) {
					TrainSeats seat = new TrainSeats(0, "CS" + i, COACH_TYPE_3A, COACH_PRICE_3A, "S" + s, true);
					seats.add(seat);
				}
			}
			for (int s = TOTAL_COACH_1A + TOTAL_COACH_2A + TOTAL_COACH_3A + 1; s <= TOTAL_COACH_1A + TOTAL_COACH_2A
					+ TOTAL_COACH_3A + TOTAL_COACH_SL; s++) {
				for (int i = 1; i <= TOTAL_SEAT_PER_COACH_SL; i++) {
					TrainSeats seat = new TrainSeats(0, "CS" + i, COACH_TYPE_SL, COACH_PRICE_SL, "S" + s, true);
					seats.add(seat);
				}
			}
			for (int s = TOTAL_COACH_1A + TOTAL_COACH_2A + TOTAL_COACH_3A + TOTAL_COACH_SL + 1; s <= TOTAL_COACH_1A
					+ TOTAL_COACH_2A + TOTAL_COACH_3A + TOTAL_COACH_SL + TOTAL_COACH_CC; s++) {
				for (int i = 1; i <= TOTAL_SEAT_PER_COACH_CC; i++) {
					TrainSeats seat = new TrainSeats(0, "CS" + i, COACH_TYPE_CC, COACH_PRICE_CC, "S" + s, true);
					seats.add(seat);
				}
			}

			Train.get().setTrainSeats(seats);

			return trainRepository.save(Train.get());
		} else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");
	}

	@Override
	public TrainBookingDetails paymentStatusChangeByTrainBookingId(long bookingid) {
		Optional<TrainBookingDetails> fbd = trainBookingDetailsRepository.findById(bookingid);
		if (fbd.isPresent()) {
			fbd.get().setPaymentStatus("Payment done");
			return trainBookingDetailsRepository.save(fbd.get());
		} else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + bookingid + " are not found");

	}

	@Override
	public TrainBookingDetails getTrainBookingDetailsByTrainBookingId(long id) {
		Optional<TrainBookingDetails> TrainBookingDetails = trainBookingDetailsRepository.findById(id);
		if (!TrainBookingDetails.isEmpty())
			return TrainBookingDetails.get();
		else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");

	}

	@Override
	public List<TrainBookingDetails> getTrainBookingDetailsByUserName(String username) {
		List<TrainBookingDetails> TrainBookingDetails = trainBookingDetailsRepository.findAll().stream()
				.filter(b -> b.getUsername().equalsIgnoreCase(username))
				.sorted((b1, b2) -> b2.getBookedDate().compareTo(b1.getBookedDate())).collect(Collectors.toList());
		if (!TrainBookingDetails.isEmpty())
			return TrainBookingDetails;
		else
			throw new TrainDetailsNotFoundException("Train details of Username: " + username + " are not found");

	}

}
