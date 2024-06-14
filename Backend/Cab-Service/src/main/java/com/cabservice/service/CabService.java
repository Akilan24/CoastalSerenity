package com.cabservice.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.cabservice.entity.Cab;
import com.cabservice.entity.CabBookingDetails;
import com.cabservice.entity.TripDetails;

@Service
public interface CabService {
	List<Cab> getAllCabs();

	Optional<Cab> getCabById(long id);

	Cab saveCab(Cab Cab);

	CabBookingDetails bookCab(long id, LocalDateTime departureTime, LocalDateTime returnTime, String journeyType,int tripId,
			String username);

	CabBookingDetails paymentstatuschange(long bookingid);

	Cab updateCab(long id, Cab Cab);

	String deleteCab(long id);

	CabBookingDetails resetStatus(long id);

	List<List<String>> getAllCityNames();

	CabBookingDetails getCabBookingDetailsById(long id);

	List<CabBookingDetails> getCabBookingDetailsByUsername(String username);
	
	TripDetails saveTrip(TripDetails trip);

	TripDetails updateTrip(int id, TripDetails tripDetails);

	String deleteTrip(int id);

	List<TripDetails> getAllTrips();

	Optional<TripDetails> getTripDetailById(int id);

}
