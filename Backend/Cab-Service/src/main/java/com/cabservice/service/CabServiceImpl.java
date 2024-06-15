package com.cabservice.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabservice.entity.BookingDetails;
import com.cabservice.entity.BookingRequest;
import com.cabservice.entity.Cab;
import com.cabservice.entity.CabDetailsTripDetails;
import com.cabservice.entity.RentalCab;
import com.cabservice.entity.RentalCabsRentalPackageDetails;
import com.cabservice.entity.RentalPackage;
import com.cabservice.entity.TripDetails;
import com.cabservice.exception.CabDetailsNotFoundException;
import com.cabservice.externalclass.Registration;
import com.cabservice.proxy.UserProxy;
import com.cabservice.repository.CabBookingDetailsRepository;
import com.cabservice.repository.CabRepository;
import com.cabservice.repository.RentalCabRepository;
import com.cabservice.repository.RentalPackageRepository;
import com.cabservice.repository.TripDetailsRepository;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	private CabRepository CabRepository;

	@Autowired
	private CabBookingDetailsRepository CabBookingDetailsRepository;

	@Autowired
	private TripDetailsRepository tripDetailsRepository;

	@Autowired
	private RentalCabRepository rentalCabRepository;
	
	@Autowired
	private RentalPackageRepository rentalPackageRepository;

	@Autowired
	UserProxy uproxy;

	@Override
	public List<Cab> getAllCabs() {
		List<Cab> list = CabRepository.findAll();
		if (!list.isEmpty())
			return list;
		else
			throw new CabDetailsNotFoundException("Cab details are not found");
	}

	@Override
	public Optional<Cab> getCabById(long id) {
		Optional<Cab> Cab = CabRepository.findById(id);
		if (!Cab.isEmpty())
			return Cab;
		else
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + id + " are not found");
	}

	@Override
	public Cab saveCab(Cab Cab) {
		long MIN_id = 100000;
		int count = CabRepository.findAll().size();
		Cab.setCabId(count == 0 ? MIN_id : MIN_id + count);
		return CabRepository.save(Cab);
	}

	@Override
	public String deleteCab(long id) {
		Optional<Cab> Cab = CabRepository.findById(id);
		if (!Cab.isEmpty()) {
			CabRepository.deleteById(id);
			return "Cab details are deleted";
		} else
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + id + " are not found");

	}

	@Override
	public Cab updateCab(long id, Cab cab) {
		Optional<Cab> f = CabRepository.findById(id);
		if (!f.isEmpty()) {
			f.get().setCabModel(cab.getCabModel());
			f.get().setCabType(cab.getCabType());
			f.get().setCabImage(cab.getCabImage());
			f.get().setTotalSeat(cab.getTotalSeat());
			f.get().setCabPrice(cab.getCabPrice());
			return CabRepository.save(f.get());
		} else
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + id + " are not found");

	}

	@Override
	public BookingDetails resetStatus(long id) {
		Optional<BookingDetails> fbd = CabBookingDetailsRepository.findById(id);
		if (fbd.isPresent()) {
			fbd.get().setPaymentStatus("Payment Cancelled & Refunded");
			return CabBookingDetailsRepository.save(fbd.get());
		} else
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + id + " are not found");

	}

	@Override
	public List<List<String>> getAllCityNames() {
		List<TripDetails> list = tripDetailsRepository.findAll();
		if (!list.isEmpty()) {
			List<String> origins = list.stream().map(TripDetails::getOrigin).distinct().collect(Collectors.toList());
			List<String> destinations = list.stream().map(TripDetails::getDestination).distinct()
					.collect(Collectors.toList());
			List<List<String>> cityNames = new ArrayList<>();
			cityNames.add(origins);
			cityNames.add(destinations);
			return cityNames;
		} else
			throw new CabDetailsNotFoundException("Cab details are not found");

	}

	public static boolean isArrialNextDay(LocalDateTime departureTime, LocalDateTime arrivalTime) {
		LocalDateTime nextDayOfDeparture = departureTime.plusDays(1).truncatedTo(ChronoUnit.DAYS);
		LocalDateTime arrivalDate = arrivalTime.truncatedTo(ChronoUnit.DAYS);

		return arrivalDate.equals(nextDayOfDeparture);
	}

	@Override
	public BookingDetails bookCab(long id,String username,BookingRequest bookingRequest) {
		Optional<Cab> CabOptional = CabRepository.findById(id);
		if (CabOptional.isEmpty()) {
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + id + " are not found");
		}
		Cab cab = CabOptional.get();

		long MIN_ID = 100000;
		int count = CabBookingDetailsRepository.findAll().size();

		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setCabBookingId(count == 0 ? MIN_ID : MIN_ID + count);
		bookingDetails.setCabModel(cab.getCabModel());
		bookingDetails.setOrigin(bookingRequest.getFrom());
		bookingDetails.setDestination(bookingRequest.getTo());
		bookingDetails.setCabModel(cab.getCabModel());
		bookingDetails.setDepartureTime(bookingRequest.getDepartDate().atTime(bookingRequest.getDepartTime()));
		if (bookingRequest.getJourneyType().equalsIgnoreCase("roundTrip")) {
			bookingDetails.setJourneyType("Round Trip");
			bookingDetails.setReturnTime(bookingRequest.getReturnDate().atTime(bookingRequest.getReturnTime()));
			Duration duration = Duration.between(bookingDetails.getDepartureTime(), bookingDetails.getReturnTime());
			long totalMinutes = duration.toMinutes();
			bookingDetails.setDuration(totalMinutes / 60 + "hrs : " + totalMinutes % 60 + "mins");
			bookingDetails.setCabPrice(cab.getCabPrice() * 2);
		} else {
			bookingDetails.setJourneyType("One way");
			bookingDetails.setCabPrice(cab.getCabPrice());
			bookingDetails.setDuration(bookingRequest.getDuration());
		}
		Registration user = uproxy.showUserByUserName(username).getBody();
		bookingDetails.setName(user.getName());
		bookingDetails.setEmail(user.getEmail());
		bookingDetails.setPhonenumber(user.getMobile());
		bookingDetails.setUsername(username);
		bookingDetails.setBookedDate(LocalDateTime.now());
		return CabBookingDetailsRepository.save(bookingDetails);

	}

	@Override
	public BookingDetails paymentstatuschange(long bookingid) {
		Optional<BookingDetails> fbd = CabBookingDetailsRepository.findById(bookingid);
		if (fbd.isPresent()) {
			fbd.get().setPaymentStatus("Payment done");
			return CabBookingDetailsRepository.save(fbd.get());
		} else
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + bookingid + " are not found");

	}

	@Override
	public BookingDetails getCabBookingDetailsById(long id) {
		Optional<BookingDetails> CabBookingDetails = CabBookingDetailsRepository.findById(id);
		if (!CabBookingDetails.isEmpty())
			return CabBookingDetails.get();
		else
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + id + " are not found");

	}

	@Override
	public List<BookingDetails> getCabBookingDetailsByUsername(String username) {
		List<BookingDetails> CabBookingDetails = CabBookingDetailsRepository.findAll().stream()
				.filter(f -> f.getUsername().equalsIgnoreCase(username))
				.sorted((f1, f2) -> f2.getBookedDate().compareTo(f1.getBookedDate())).collect(Collectors.toList());
		if (!CabBookingDetails.isEmpty())
			return CabBookingDetails;
		else
			throw new CabDetailsNotFoundException("Cab details of Username: " + username + " are not found");

	}

	@Override
	public TripDetails saveTrip(TripDetails trip) {
		return tripDetailsRepository.save(trip);
	}

	@Override
	public TripDetails updateTrip(int id, TripDetails tripDetails) {

		Optional<TripDetails> trip = tripDetailsRepository.findById(id);
		if (trip.isPresent()) {
			trip.get().setOrigin(tripDetails.getOrigin());
			trip.get().setDestination(tripDetails.getDestination());
			trip.get().setDistance(tripDetails.getDistance());
			return tripDetailsRepository.save(trip.get());
		} else
			throw new CabDetailsNotFoundException("Trip details of trip id: " + id + " are not found");

	}

	@Override
	public String deleteTrip(int id) {
		Optional<TripDetails> trip = tripDetailsRepository.findById(id);
		if (trip.isPresent()) {
			tripDetailsRepository.deleteById(id);
			return "Trip details of trip id: " + id + " are deleted";
		} else
			throw new CabDetailsNotFoundException("Trip details of trip id: " + id + " are not found");
	}

	@Override
	public List<TripDetails> getAllTrips() {
		List<TripDetails> tripList = tripDetailsRepository.findAll();
		if (!tripList.isEmpty()) {
			return tripList;
		} else
			throw new CabDetailsNotFoundException("Trip details are not found");

	}

	@Override
	public Optional<TripDetails> getTripDetailById(int id) {
		Optional<TripDetails> trip = tripDetailsRepository.findById(id);
		if (trip.isPresent()) {
			return trip;
		} else
			throw new CabDetailsNotFoundException("Trip details of trip id: " + id + " are not found");

	}

	@Override
	public RentalCab saveRentalCab(RentalCab rentalCab) {
		return rentalCabRepository.save(rentalCab);
	}

	@Override
	public RentalCab updateRentalCab(int rentalCabId, RentalCab rentalCab) {
		Optional<RentalCab> cab = rentalCabRepository.findById(rentalCabId);
		if (!cab.isEmpty()) {
			cab.get().setCabImage(rentalCab.getCabImage());
			cab.get().setCabType(rentalCab.getCabType());
			cab.get().setTotalSeat(rentalCab.getTotalSeat());
			return rentalCabRepository.save(cab.get());
		} else
			throw new CabDetailsNotFoundException("RentalCab details of  rental cab id: " + rentalCabId + " are not found");

	}

	@Override
	public String deleteRentalCab(int rentalCabId) {
		Optional<RentalCab> cab = rentalCabRepository.findById(rentalCabId);
		if (!cab.isEmpty()) {
			rentalCabRepository.deleteById(rentalCabId);
			return "Cab details of cab model: " + rentalCabId + " are deleted";
		} else
			throw new CabDetailsNotFoundException("RentalCab details of  rental cab id: " + rentalCabId + " are not found");

	}

	@Override
	public List<RentalCab> getAllRentalCab() {
		List<RentalCab> rentalCabList = rentalCabRepository.findAll();
		if (!rentalCabList.isEmpty()) {
			return rentalCabList;
		} else
			throw new CabDetailsNotFoundException("RentalCab details are not found");

	}

	@Override
	public Optional<RentalCab> getRentalCabById(int rentalCabId) {
		Optional<RentalCab> cab = rentalCabRepository.findById(rentalCabId);
		if (!cab.isEmpty()) {
			return cab;
		} else
			throw new CabDetailsNotFoundException("RentalCab details of  rental cab id: " + rentalCabId + " are not found");

	}

	@Override
	public RentalPackage saveRentalPackage(RentalPackage rentalPackage) {
		return rentalPackageRepository.save(rentalPackage);	}

	@Override
	public RentalPackage updateRentalPackage(int rentalPackageId, RentalPackage rentalPackage) {
		Optional<RentalPackage> rental = rentalPackageRepository.findById(rentalPackageId);
		if (!rental.isEmpty()) {
			rental.get().setDurationPackage(rentalPackage.getDurationPackage());
			rental.get().setFrom(rentalPackage.getFrom());
			return rentalPackageRepository.save(rental.get());
		} else
			throw new CabDetailsNotFoundException("RentalPackage details of  rentalPackage id: " + rentalPackageId + " are not found");

	}

	@Override
	public String deleteRentalPackage(int rentalPackageId) {
		Optional<RentalPackage> rentalPackage = rentalPackageRepository.findById(rentalPackageId);
		if (!rentalPackage.isEmpty()) {
			rentalPackageRepository.deleteById(rentalPackageId);
			return "RentalPackage details of rentalPackage id: " + rentalPackageId + " are deleted";
		} else
			throw new CabDetailsNotFoundException("RentalPackage details of  rentalPackage id: " + rentalPackageId + " are not found");


	}

	@Override
	public List<RentalPackage> getAllRentalPackage() {
		List<RentalPackage> rentalPackageList = rentalPackageRepository.findAll();
		if (!rentalPackageList.isEmpty()) {
			return rentalPackageList;
		} else
			throw new CabDetailsNotFoundException("RentalCab details are not found");

	}

	@Override
	public Optional<RentalPackage> getRentalPackageById(int rentalPackageId) {
		Optional<RentalPackage> rentalPackage = rentalPackageRepository.findById(rentalPackageId);
		if (!rentalPackage.isEmpty()) {
			return rentalPackage;
		} else
			throw new CabDetailsNotFoundException("RentalPackage details of  rentalPackage id: " + rentalPackageId + " are not found");

	}

	@Override
	public List<String> getAllRentalCityNames() {
		List<RentalPackage> rentalPackageList = rentalPackageRepository.findAll();
		if (!rentalPackageList.isEmpty()) {
			List<String> cityList=new ArrayList<String>();
			for(RentalPackage rp: rentalPackageList) {
				cityList.add(rp.getFrom());
				}
			return cityList;
		} else
			throw new CabDetailsNotFoundException("RentalCab details are not found");

	}

	@Override
	public CabDetailsTripDetails getCabDetailsAndTripDetails(String from, String to) {
		CabDetailsTripDetails cdtd=new CabDetailsTripDetails();
		List<Cab> list = CabRepository.findAll();
		if (!list.isEmpty()) {
		    cdtd.setCabs(list);
		   List<TripDetails> tripList= tripDetailsRepository.findAll().stream().filter(t->t.getOrigin().equalsIgnoreCase(from)).filter(t->t.getDestination().equalsIgnoreCase(to)).collect(Collectors.toList());
		   if(!tripList.isEmpty()) {
			   cdtd.setTripDetails(tripList.get(0));
			   return cdtd;
		   }else {
			   throw new CabDetailsNotFoundException("Trip details are not found");
		   }
		}
		else
			throw new CabDetailsNotFoundException("Cab details are not found");
	
	}

	@Override
	public RentalCabsRentalPackageDetails getRentalCabAndRentalPackageDetails(String from) {
		RentalCabsRentalPackageDetails rcrp=new RentalCabsRentalPackageDetails();
		List<RentalCab> list = rentalCabRepository.findAll();
		if (!list.isEmpty()) {
			rcrp.setRentalCabs(list);
		   List<RentalPackage> rentalPackageList= rentalPackageRepository.findAll().stream().filter(t->t.getFrom().equalsIgnoreCase(from)).collect(Collectors.toList());
		   if(!rentalPackageList.isEmpty()) {
			   rcrp.setRentalPackage(rentalPackageList.get(0));
			   return rcrp;
		   }else {
			   throw new CabDetailsNotFoundException("Trip details are not found");
		   }
		}
		else
			throw new CabDetailsNotFoundException("Cab details are not found");
	
	}
}
