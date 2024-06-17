package com.cabservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cabservice.entity.BookingRequest;
import com.cabservice.entity.Cab;
import com.cabservice.entity.CabBookingDetails;
import com.cabservice.entity.CabDetailsTripDetails;
import com.cabservice.entity.RentalCab;
import com.cabservice.entity.RentalCabBookingDetails;
import com.cabservice.entity.RentalCabsRentalPackageDetails;
import com.cabservice.entity.RentalPackage;
import com.cabservice.entity.TripDetails;

@Service
public interface CabService {
	List<Cab> getAllCabs();

	Optional<Cab> getCabById(long id);

	Cab saveCab(Cab Cab);

	CabBookingDetails bookCab(long id, String username, BookingRequest bookingRequest);
	
	RentalCabBookingDetails bookRentalCab(long id, String username, BookingRequest bookingRequest);

	CabBookingDetails paymentstatuschangeCab(long bookingid);
	
	RentalCabBookingDetails paymentstatuschangeRentalCab(long bookingid);

	Cab updateCab(long id, Cab Cab);

	String deleteCab(long id);

	CabBookingDetails resetStatusCab(long id);
	
	RentalCabBookingDetails resetStatusRentalCab(long id);

	List<List<String>> getAllCityNames();

	CabBookingDetails getCabBookingDetailsById(long id);

	List<CabBookingDetails> getCabBookingDetailsByUsername(String username);

	RentalCabBookingDetails getRentalCabBookingDetailsById(long id);

	List<RentalCabBookingDetails> getRentalCabBookingDetailsByUsername(String username);
	
	TripDetails saveTrip(TripDetails trip);

	TripDetails updateTrip(int id, TripDetails tripDetails);

	String deleteTrip(int id);

	List<TripDetails> getAllTrips();

	Optional<TripDetails> getTripDetailById(int id);

	RentalCab saveRentalCab(RentalCab rentalCab);

	RentalCab updateRentalCab(long rentalCabId, RentalCab rentalCab);

	String deleteRentalCab(long rentalCabId);

	List<RentalCab> getAllRentalCab();

	Optional<RentalCab> getRentalCabById(long rentalCabId);

	RentalPackage saveRentalPackage(RentalPackage rentalPackage);

	List<String> getAllRentalCityNames();

	RentalPackage updateRentalPackage(int rentalPackageId, RentalPackage rentalPackage);

	String deleteRentalPackage(int rentalPackageId);

	List<RentalPackage> getAllRentalPackage();

	Optional<RentalPackage> getRentalPackageById(int rentalPackageId);

	CabDetailsTripDetails getCabDetailsAndTripDetails(String from, String to);

	RentalCabsRentalPackageDetails getRentalCabAndRentalPackageDetails(String from,String packageName);

}
