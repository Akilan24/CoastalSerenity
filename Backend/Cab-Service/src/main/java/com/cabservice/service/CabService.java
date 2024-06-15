package com.cabservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cabservice.entity.BookingDetails;
import com.cabservice.entity.BookingRequest;
import com.cabservice.entity.Cab;
import com.cabservice.entity.CabDetailsTripDetails;
import com.cabservice.entity.RentalCab;
import com.cabservice.entity.RentalCabsRentalPackageDetails;
import com.cabservice.entity.RentalPackage;
import com.cabservice.entity.TripDetails;

@Service
public interface CabService {
	List<Cab> getAllCabs();

	Optional<Cab> getCabById(long id);

	Cab saveCab(Cab Cab);

	BookingDetails bookCab(long id, String username, BookingRequest bookingRequest);

	BookingDetails paymentstatuschange(long bookingid);

	Cab updateCab(long id, Cab Cab);

	String deleteCab(long id);

	BookingDetails resetStatus(long id);

	List<List<String>> getAllCityNames();

	BookingDetails getCabBookingDetailsById(long id);

	List<BookingDetails> getCabBookingDetailsByUsername(String username);

	TripDetails saveTrip(TripDetails trip);

	TripDetails updateTrip(int id, TripDetails tripDetails);

	String deleteTrip(int id);

	List<TripDetails> getAllTrips();

	Optional<TripDetails> getTripDetailById(int id);

	RentalCab saveRentalCab(RentalCab rentalCab);

	RentalCab updateRentalCab(int rentalCabId, RentalCab rentalCab);

	String deleteRentalCab(int rentalCabId);

	List<RentalCab> getAllRentalCab();

	Optional<RentalCab> getRentalCabById(int rentalCabId);

	RentalPackage saveRentalPackage(RentalPackage rentalPackage);

	List<String> getAllRentalCityNames();

	RentalPackage updateRentalPackage(int rentalPackageId, RentalPackage rentalPackage);

	String deleteRentalPackage(int rentalPackageId);

	List<RentalPackage> getAllRentalPackage();

	Optional<RentalPackage> getRentalPackageById(int rentalPackageId);

	CabDetailsTripDetails getCabDetailsAndTripDetails(String from, String to);

	RentalCabsRentalPackageDetails getRentalCabAndRentalPackageDetails(String from);

}
