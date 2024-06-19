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

	Optional<Cab> getCabByCabId(long id);

	Cab saveCab(Cab Cab);

	CabBookingDetails bookCabByCabId(long id, String username, BookingRequest bookingRequest);

	RentalCabBookingDetails bookRentalCabByRentalCabId(long id, String username, BookingRequest bookingRequest);

	CabBookingDetails paymentStatusChangeByCabBookingId(long bookingid);

	RentalCabBookingDetails paymentStatusChangeByRentalCabBookingId(long bookingid);

	Cab updateCabByCabId(long id, Cab Cab);

	String deleteCabByCabId(long id);

	CabBookingDetails cancelPaymentByCabBookingId(long id);

	RentalCabBookingDetails cancelPaymentByRentalCabBookingId(long id);

	List<List<String>> getAllCityNames();

	CabBookingDetails getCabBookingDetailsByCabBookingId(long id);

	List<CabBookingDetails> getCabBookingDetailsByUsername(String username);

	RentalCabBookingDetails getRentalCabBookingDetailsByRentalCabBookingId(long id);

	List<RentalCabBookingDetails> getRentalCabBookingDetailsByUsername(String username);

	TripDetails saveTrip(TripDetails trip);

	TripDetails updateTripByTripId(int id, TripDetails tripDetails);

	String deleteTripByTripId(int id);

	List<TripDetails> getAllTrips();

	Optional<TripDetails> getTripByTripId(int id);

	RentalCab saveRentalCab(RentalCab rentalCab);

	RentalCab updateRentalCabByRentalCabId(long rentalCabId, RentalCab rentalCab);

	String deleteRentalCabByRentalCabId(long rentalCabId);

	List<RentalCab> getAllRentalCab();

	Optional<RentalCab> getRentalCabByRentalCabId(long rentalCabId);

	RentalPackage saveRentalPackage(RentalPackage rentalPackage);

	List<String> getAllRentalCityNames();

	RentalPackage updateRentalPackageByRentalPackageId(int rentalPackageId, RentalPackage rentalPackage);

	String deleteRentalPackageByRentalPackageId(int rentalPackageId);

	List<RentalPackage> getAllRentalPackage();

	Optional<RentalPackage> getRentalPackageByByRentalPackageId(int rentalPackageId);

	CabDetailsTripDetails getCabDetailsAndTripDetails(String from, String to);

	RentalCabsRentalPackageDetails getRentalCabAndRentalPackageDetails(String from, String packageName);

}
