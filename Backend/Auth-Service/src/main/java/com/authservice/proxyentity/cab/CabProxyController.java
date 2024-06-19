package com.authservice.proxyentity.cab;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.authservice.constant.CabConstant;

@FeignClient(name = CabConstant.SERVICE, url = CabConstant.URL)
public interface CabProxyController {

	@GetMapping(CabConstant.GET_CAB_BY_CAB_ID)
	ResponseEntity<List<Cab>> getAllCabs();

	@GetMapping(CabConstant.ADD_SEATS_BY_CAB_ID)
	ResponseEntity<Optional<Cab>> getCabById(@PathVariable("id") long id);

	@PostMapping(CabConstant.SAVE_CAB)
	ResponseEntity<Cab> createCab(@RequestBody Cab cab);

	@PostMapping(CabConstant.BOOK_CAB_BY_CAB_ID)
	public ResponseEntity<CabBookingDetails> bookCab(@PathVariable String id, @PathVariable String username,
			@RequestBody BookingRequest bookingRequest);

	@PostMapping(CabConstant.BOOK_RENTAL_CAB_BY_CAB_ID)
	public ResponseEntity<RentalCabBookingDetails> bookRentalCab(@PathVariable long id, @PathVariable String username,
			@RequestBody BookingRequest bookingRequest);

	@GetMapping(CabConstant.GET_CAB_BOOKING_DETAILS_BY_CAB_BOOKING_ID)
	ResponseEntity<CabBookingDetails> getCabBookingDetailsById(@PathVariable("id") long id);

	@GetMapping(CabConstant.GET_CAB_BOOKING_DETAILS_BY_USERNAME)
	ResponseEntity<List<CabBookingDetails>> getCabBookingDetailsByUsername(@PathVariable("username") String username);

	@GetMapping(CabConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_RENTAL_CAB_BOOKING_ID)
	ResponseEntity<RentalCabBookingDetails> getRentalCabBookingDetailsByRentalCabBookingId(@PathVariable long id);

	@GetMapping(CabConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_USERNAME)
	ResponseEntity<List<RentalCabBookingDetails>> getRentalCabBookingDetailsByUsername(@PathVariable String username);

	@PutMapping(CabConstant.UPDATE_CAB_BY_CAB_ID)
	ResponseEntity<Cab> updateCab(@PathVariable("id") long id, @RequestBody Cab cab);

	@DeleteMapping(CabConstant.DELETE_CAB_BY_CAB_ID)
	ResponseEntity<String> deleteCab(@PathVariable("id") long id);

	@PutMapping(CabConstant.CANCEL_PAYMENT_BY_CAB_BOOKING_ID)
	ResponseEntity<CabBookingDetails> resetstatusCab(@PathVariable("id") long id);

	@PutMapping(CabConstant.CANCEL_PAYMENT_BY_RENTAL_CAB_BOOKING_ID)
	ResponseEntity<CabBookingDetails> resetstatusRentalCab(@PathVariable("id") long id);

	@GetMapping(CabConstant.GET_ALL_CITY_NAMES)
	ResponseEntity<List<List<String>>> getAllCityNames();

	@GetMapping(CabConstant.PAYMENT_STATUS_CHANGE_BY_CAB_BOOKING_ID)
	ResponseEntity<CabBookingDetails> paymentstatuschangeCab(@PathVariable("bookingid") long bookingid);

	@GetMapping(CabConstant.PAYMENT_STATUS_CHANGE_BY_RENTAL_CAB_BOOKING_ID)
	ResponseEntity<CabBookingDetails> paymentstatuschangeRentalCab(@PathVariable("bookingid") long bookingid);

	@GetMapping(CabConstant.GET_CAB_DETAILS_AND_TRIP_DETAILS)
	public ResponseEntity<CabDetailsTripDetails> getCabDetailsAndTripDetails(@RequestParam String from,
			@RequestParam String to);

	@GetMapping(CabConstant.GET_RENTAL_CAB_AND_RENTAL_PACKAGE_DETAILS)
	public ResponseEntity<RentalCabsRentalPackageDetails> getRentalCabAndRentalPackageDetails(@RequestParam String from,
			@RequestParam String packageName);

	@PostMapping(CabConstant.SAVE_TRIP)
	ResponseEntity<TripDetails> saveTrip(@RequestBody TripDetails trip);

	@PutMapping(CabConstant.UPDATE_TRIP_BY_TRIP_ID)
	ResponseEntity<TripDetails> updateTrip(@PathVariable("tripId") int tripId, @RequestBody TripDetails trip);

	@DeleteMapping(CabConstant.DELETE_TRIP_BY_TRIP_ID)
	ResponseEntity<String> deleteTrip(@PathVariable("tripId") int tripId);

	@GetMapping(CabConstant.GET_ALL_TRIP)
	ResponseEntity<List<TripDetails>> getAllTrip();

	@GetMapping(CabConstant.GET_TRIP_BY_TRIP_ID)
	ResponseEntity<Optional<TripDetails>> getTripById(@PathVariable("tripId") int tripId);

	@PostMapping(CabConstant.SAVE_RENTAL_CAB)
	ResponseEntity<RentalCab> saveRentalCab(@RequestBody RentalCab rentalCab);

	@PutMapping(CabConstant.UPDATE_RENTAL_CAB_BY_RENTAL_CAB_ID)
	ResponseEntity<RentalCab> updateRentalCab(@PathVariable("rentalCabId") int rentalCabId,
			@RequestBody RentalCab rentalCab);

	@DeleteMapping(CabConstant.DELETE_RENTAL_CAB_BY_RENTAL_CAB_ID)
	ResponseEntity<String> deleteRentalCab(@PathVariable("rentalCabId") int rentalCabId);

	@GetMapping(CabConstant.GET_ALL_RENTAL_CAB)
	ResponseEntity<List<RentalCab>> getAllRentalCab();

	@GetMapping(CabConstant.GET_RENTAL_CAB_BY_RENTAL_CAB_ID)
	ResponseEntity<Optional<RentalCab>> getRentalCabById(@PathVariable("rentalCabId") int rentalCabId);

	@PostMapping(CabConstant.SAVE_RENTAL_PACKAGE)
	ResponseEntity<RentalPackage> saveRentalPackage(@RequestBody RentalPackage rentalPackage);

	@GetMapping(CabConstant.GET_ALL_RENTAL_CITY_NAMES)
	public ResponseEntity<List<String>> getAllRentalCityNames();

	@PutMapping(CabConstant.UPDATE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	ResponseEntity<RentalPackage> updateRentalPackage(@PathVariable("rentalPackageId") int rentalPackageId,
			@RequestBody RentalPackage rentalPackage);

	@DeleteMapping(CabConstant.DELETE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	ResponseEntity<String> deleteRentalPackage(@PathVariable("rentalPackageId") int rentalPackageId);

	@GetMapping(CabConstant.GET_ALL_RENTAL_PACKAGE)
	ResponseEntity<List<RentalPackage>> getAllRentalPackage();

	@GetMapping(CabConstant.GET_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	ResponseEntity<Optional<RentalPackage>> getRentalPackageById(@PathVariable("rentalPackageId") int rentalPackageId);
}
