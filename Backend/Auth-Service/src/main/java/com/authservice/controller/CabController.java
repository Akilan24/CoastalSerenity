package com.authservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.constant.CabConstant;
import com.authservice.proxyentity.cab.BookingRequest;
import com.authservice.proxyentity.cab.Cab;
import com.authservice.proxyentity.cab.CabBookingDetails;
import com.authservice.proxyentity.cab.CabDetailsTripDetails;
import com.authservice.proxyentity.cab.CabProxyController;
import com.authservice.proxyentity.cab.RentalCab;
import com.authservice.proxyentity.cab.RentalCabBookingDetails;
import com.authservice.proxyentity.cab.RentalCabsRentalPackageDetails;
import com.authservice.proxyentity.cab.RentalPackage;
import com.authservice.proxyentity.cab.TripDetails;

@RestController
@RequestMapping(CabConstant.CAB)
@CrossOrigin(CabConstant.CROSS_ORIGIN)
public class CabController {

	@Autowired
	private CabProxyController cabProxy;

	@GetMapping(CabConstant.GET_CAB_BY_CAB_ID)
	ResponseEntity<List<Cab>> getAllCabs() {
		return cabProxy.getAllCabs();
	}

	@GetMapping(CabConstant.ADD_SEATS_BY_CAB_ID)
	ResponseEntity<Optional<Cab>> getCabById(@PathVariable("id") long id) {
		return cabProxy.getCabById(id);
	}

	@PostMapping(CabConstant.SAVE_CAB)
	ResponseEntity<Cab> createCab(@RequestBody Cab cab) {
		return cabProxy.createCab(cab);
	}

	@PostMapping(CabConstant.BOOK_CAB_BY_CAB_ID)
	public ResponseEntity<CabBookingDetails> bookCab(@PathVariable String id, @PathVariable String username,
			@RequestBody BookingRequest bookingRequest) {
		return cabProxy.bookCab(id, username, bookingRequest);
	}

	@PostMapping(CabConstant.BOOK_RENTAL_CAB_BY_CAB_ID)
	public ResponseEntity<RentalCabBookingDetails> bookRentalCab(@PathVariable long id, @PathVariable String username,
			@RequestBody BookingRequest bookingRequest) {
		return cabProxy.bookRentalCab(id, username, bookingRequest);
	}

	@GetMapping(CabConstant.GET_CAB_BOOKING_DETAILS_BY_CAB_BOOKING_ID)
	ResponseEntity<CabBookingDetails> getCabBookingDetailsById(@PathVariable("id") long id) {
		return cabProxy.getCabBookingDetailsById(id);
	}

	@GetMapping(CabConstant.GET_CAB_BOOKING_DETAILS_BY_USERNAME)
	ResponseEntity<List<CabBookingDetails>> getCabBookingDetailsByUsername(@PathVariable("username") String username) {
		return cabProxy.getCabBookingDetailsByUsername(username);
	}
	
	@GetMapping(CabConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_RENTAL_CAB_BOOKING_ID)
	 ResponseEntity<RentalCabBookingDetails> getRentalCabBookingDetailsByRentalCabBookingId(@PathVariable long id) {
		return cabProxy.getRentalCabBookingDetailsByRentalCabBookingId(id);
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_USERNAME)
	ResponseEntity<List<RentalCabBookingDetails>> getRentalCabBookingDetailsByUsername(
			@PathVariable String username) {
		return cabProxy.getRentalCabBookingDetailsByUsername(username);
	}
	
	@PutMapping(CabConstant.UPDATE_CAB_BY_CAB_ID)
	ResponseEntity<Cab> updateCab(@PathVariable("id") long id, @RequestBody Cab cab) {
		return cabProxy.updateCab(id, cab);
	}

	@DeleteMapping(CabConstant.DELETE_CAB_BY_CAB_ID)
	ResponseEntity<String> deleteCab(@PathVariable("id") long id) {
		return cabProxy.deleteCab(id);
	}

	@PutMapping(CabConstant.CANCEL_PAYMENT_BY_CAB_BOOKING_ID)
	ResponseEntity<CabBookingDetails> resetstatusCab(@PathVariable("id") long id) {
		return cabProxy.resetstatusCab(id);
	}

	@PutMapping(CabConstant.CANCEL_PAYMENT_BY_RENTAL_CAB_BOOKING_ID)
	ResponseEntity<CabBookingDetails> resetstatusRentalCab(@PathVariable("id") long id) {
		return cabProxy.resetstatusRentalCab(id);
	}

	@GetMapping(CabConstant.GET_ALL_CITY_NAMES)
	ResponseEntity<List<List<String>>> getAllCityNames() {
		return cabProxy.getAllCityNames();
	}

	@GetMapping(CabConstant.PAYMENT_STATUS_CHANGE_BY_CAB_BOOKING_ID)
	ResponseEntity<CabBookingDetails> paymentstatuschangeCab(@PathVariable("bookingid") long bookingid) {
		return cabProxy.paymentstatuschangeCab(bookingid);
	}

	@GetMapping(CabConstant.PAYMENT_STATUS_CHANGE_BY_RENTAL_CAB_BOOKING_ID)
	ResponseEntity<CabBookingDetails> paymentstatuschangeRentalCab(@PathVariable("bookingid") long bookingid) {
		return cabProxy.paymentstatuschangeRentalCab(bookingid);
	}

	@GetMapping(CabConstant.GET_CAB_DETAILS_AND_TRIP_DETAILS)
	public ResponseEntity<CabDetailsTripDetails> getCabDetailsAndTripDetails(@RequestParam String from,
			@RequestParam String to) {
		return cabProxy.getCabDetailsAndTripDetails(from, to);
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_AND_RENTAL_PACKAGE_DETAILS)
	public ResponseEntity<RentalCabsRentalPackageDetails> getRentalCabAndRentalPackageDetails(@RequestParam String from,
			@RequestParam String packageName) {
		return cabProxy.getRentalCabAndRentalPackageDetails(from, packageName);
	}

	@PostMapping(CabConstant.SAVE_TRIP)
	ResponseEntity<TripDetails> saveTrip(@RequestBody TripDetails trip) {
		return cabProxy.saveTrip(trip);
	}

	@PutMapping(CabConstant.UPDATE_TRIP_BY_TRIP_ID)
	ResponseEntity<TripDetails> updateTrip(@PathVariable("tripId") int tripId, @RequestBody TripDetails trip) {
		return cabProxy.updateTrip(tripId, trip);
	}

	@DeleteMapping(CabConstant.DELETE_TRIP_BY_TRIP_ID)
	ResponseEntity<String> deleteTrip(@PathVariable("tripId") int tripId) {
		return cabProxy.deleteTrip(tripId);
	}

	@GetMapping(CabConstant.GET_ALL_TRIP)
	ResponseEntity<List<TripDetails>> getAllTrip() {
		return cabProxy.getAllTrip();
	}

	@GetMapping(CabConstant.GET_TRIP_BY_TRIP_ID)
	ResponseEntity<Optional<TripDetails>> getTripById(@PathVariable("tripId") int tripId) {
		return cabProxy.getTripById(tripId);
	}

	@PostMapping(CabConstant.SAVE_RENTAL_CAB)
	ResponseEntity<RentalCab> saveRentalCab(@RequestBody RentalCab rentalCab) {
		return cabProxy.saveRentalCab(rentalCab);
	}

	@PutMapping(CabConstant.UPDATE_RENTAL_CAB_BY_RENTAL_CAB_ID)
	ResponseEntity<RentalCab> updateRentalCab(@PathVariable("rentalCabId") int rentalCabId,
			@RequestBody RentalCab rentalCab) {
		return cabProxy.updateRentalCab(rentalCabId, rentalCab);
	}

	@DeleteMapping(CabConstant.DELETE_RENTAL_CAB_BY_RENTAL_CAB_ID)
	ResponseEntity<String> deleteRentalCab(@PathVariable("rentalCabId") int rentalCabId) {
		return cabProxy.deleteRentalCab(rentalCabId);
	}

	@GetMapping(CabConstant.GET_ALL_RENTAL_CAB)
	ResponseEntity<List<RentalCab>> getAllRentalCab() {
		return cabProxy.getAllRentalCab();
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_BY_RENTAL_CAB_ID)
	ResponseEntity<Optional<RentalCab>> getRentalCabById(@PathVariable("rentalCabId") int rentalCabId) {
		return cabProxy.getRentalCabById(rentalCabId);
	}

	@PostMapping(CabConstant.SAVE_RENTAL_PACKAGE)
	ResponseEntity<RentalPackage> saveRentalPackage(@RequestBody RentalPackage rentalPackage) {
		return cabProxy.saveRentalPackage(rentalPackage);
	}

	@GetMapping(CabConstant.GET_ALL_RENTAL_CITY_NAMES)
	public ResponseEntity<List<String>> getAllRentalCityNames() {
		return cabProxy.getAllRentalCityNames();
	}

	@PutMapping(CabConstant.UPDATE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	ResponseEntity<RentalPackage> updateRentalPackage(@PathVariable("rentalPackageId") int rentalPackageId,
			@RequestBody RentalPackage rentalPackage) {
		return cabProxy.updateRentalPackage(rentalPackageId, rentalPackage);
	}

	@DeleteMapping(CabConstant.DELETE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	ResponseEntity<String> deleteRentalPackage(@PathVariable("rentalPackageId") int rentalPackageId) {
		return cabProxy.deleteRentalPackage(rentalPackageId);
	}

	@GetMapping(CabConstant.GET_ALL_RENTAL_PACKAGE)
	ResponseEntity<List<RentalPackage>> getAllRentalPackage() {
		return cabProxy.getAllRentalPackage();
	}

	@GetMapping(CabConstant.GET_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	ResponseEntity<Optional<RentalPackage>> getRentalPackageById(@PathVariable("rentalPackageId") int rentalPackageId) {
		return cabProxy.getRentalPackageById(rentalPackageId);
	}
}
