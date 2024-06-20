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
	public ResponseEntity<List<Cab>> getAllCab();

	@GetMapping(CabConstant.ADD_SEATS_BY_CAB_ID)
	public ResponseEntity<Optional<Cab>> getCabByCabId(@PathVariable long id);

	@PostMapping(CabConstant.SAVE_CAB)
	public ResponseEntity<Cab> saveCab(@RequestBody Cab Cab);

	@PostMapping(CabConstant.BOOK_CAB_BY_CAB_ID)
	public ResponseEntity<CabBookingDetails> bookCabByCabId(@PathVariable long id, @PathVariable String username,
			@RequestBody BookingRequest bookingRequest);

	@PostMapping(CabConstant.BOOK_RENTAL_CAB_BY_CAB_ID)
	public ResponseEntity<RentalCabBookingDetails> bookRentalCabByRentalCabId(@PathVariable long id,
			@PathVariable String username, @RequestBody BookingRequest bookingRequest);

	@GetMapping(CabConstant.GET_CAB_BOOKING_DETAILS_BY_CAB_BOOKING_ID)
	public ResponseEntity<CabBookingDetails> getCabBookingDetailsByCabBookingId(@PathVariable long id);

	@GetMapping(CabConstant.GET_CAB_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<CabBookingDetails>> getCabBookingDetailsByUsername(@PathVariable String username);

	@GetMapping(CabConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_RENTAL_CAB_BOOKING_ID)
	public ResponseEntity<RentalCabBookingDetails> getRentalCabBookingDetailsByRentalCabBookingId(
			@PathVariable long id);

	@GetMapping(CabConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<RentalCabBookingDetails>> getRentalCabBookingDetailsByUsername(
			@PathVariable String username);

	@PutMapping(CabConstant.UPDATE_CAB_BY_CAB_ID)
	public ResponseEntity<Cab> updateCabByCabId(@PathVariable long id, @RequestBody Cab Cab);

	@DeleteMapping(CabConstant.DELETE_CAB_BY_CAB_ID)
	public ResponseEntity<String> deleteCabByCabId(@PathVariable long id);

	@PutMapping(CabConstant.CANCEL_PAYMENT_BY_CAB_BOOKING_ID)
	public ResponseEntity<CabBookingDetails> cancelPaymentByCabBookingId(@PathVariable long id);

	@PutMapping(CabConstant.CANCEL_PAYMENT_BY_RENTAL_CAB_BOOKING_ID)
	public ResponseEntity<RentalCabBookingDetails> cancelPaymentByRentalCabBookingId(@PathVariable long id);

	@GetMapping(CabConstant.GET_ALL_CITY_NAMES)
	public ResponseEntity<List<List<String>>> getAllCityNames();

	@GetMapping(CabConstant.PAYMENT_STATUS_CHANGE_BY_CAB_BOOKING_ID)
	public ResponseEntity<CabBookingDetails> paymentStatusChangeByCabBookingId(@PathVariable long bookingid);

	@GetMapping(CabConstant.PAYMENT_STATUS_CHANGE_BY_RENTAL_CAB_BOOKING_ID)
	public ResponseEntity<RentalCabBookingDetails> paymentStatusChangeByRentalCabBookingId(
			@PathVariable long bookingid);

	@GetMapping(CabConstant.GET_CAB_DETAILS_AND_TRIP_DETAILS)
	public ResponseEntity<CabDetailsTripDetails> getCabDetailsAndTripDetails(@RequestParam String from,
			@RequestParam String to);

	@GetMapping(CabConstant.GET_RENTAL_CAB_AND_RENTAL_PACKAGE_DETAILS)
	public ResponseEntity<RentalCabsRentalPackageDetails> getRentalCabAndRentalPackageDetails(@RequestParam String from,
			@RequestParam String packageName);

	@PostMapping(CabConstant.SAVE_TRIP)
	public ResponseEntity<TripDetails> saveTrip(@RequestBody TripDetails trip);

	@PutMapping(CabConstant.UPDATE_TRIP_BY_TRIP_ID)
	public ResponseEntity<TripDetails> updateTripByTripId(@PathVariable int tripId, @RequestBody TripDetails trip);

	@DeleteMapping(CabConstant.DELETE_TRIP_BY_TRIP_ID)
	public ResponseEntity<String> deleteTripByTripId(@PathVariable int tripId);

	@GetMapping(CabConstant.GET_ALL_TRIP)
	public ResponseEntity<List<TripDetails>> getAllTrip();

	@GetMapping(CabConstant.GET_TRIP_BY_TRIP_ID)
	public ResponseEntity<Optional<TripDetails>> getTripByTripId(@PathVariable int tripId);

	@PostMapping(CabConstant.SAVE_RENTAL_CAB)
	public ResponseEntity<RentalCab> saveRentalCab(@RequestBody RentalCab rentalCab);

	@PutMapping(CabConstant.UPDATE_RENTAL_CAB_BY_RENTAL_CAB_ID)
	public ResponseEntity<RentalCab> updateRentalCabByRentalCabId(@PathVariable long rentalCabId,
			@RequestBody RentalCab rentalCab);

	@DeleteMapping(CabConstant.DELETE_RENTAL_CAB_BY_RENTAL_CAB_ID)
	public ResponseEntity<String> deleteRentalCabByRentalCabId(@PathVariable long rentalCabId);

	@GetMapping(CabConstant.GET_ALL_RENTAL_CAB)
	public ResponseEntity<List<RentalCab>> getAllRentalCab();

	@GetMapping(CabConstant.GET_RENTAL_CAB_BY_RENTAL_CAB_ID)
	public ResponseEntity<Optional<RentalCab>> getRentalCabByRentalCabId(@PathVariable long rentalCabId);

	@PostMapping(CabConstant.SAVE_RENTAL_PACKAGE)
	public ResponseEntity<RentalPackage> saveRentalPackage(@RequestBody RentalPackage rentalPackage);

	@GetMapping(CabConstant.GET_ALL_RENTAL_CITY_NAMES)
	public ResponseEntity<List<String>> getAllRentalCityNames();

	@PutMapping(CabConstant.UPDATE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	public ResponseEntity<RentalPackage> updateRentalPackageByRentalPackageId(@PathVariable int rentalPackageId,
			@RequestBody RentalPackage rentalPackage);

	@DeleteMapping(CabConstant.DELETE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	public ResponseEntity<String> deleteRentalPackageByRentalPackageId(@PathVariable int rentalPackageId);

	@GetMapping(CabConstant.GET_ALL_RENTAL_PACKAGE)
	public ResponseEntity<List<RentalPackage>> getAllRentalPackage();

	@GetMapping(CabConstant.GET_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	public ResponseEntity<Optional<RentalPackage>> getRentalPackageByRentalPackageId(@PathVariable int rentalPackageId);
}
