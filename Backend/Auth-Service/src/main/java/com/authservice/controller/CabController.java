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

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(CabConstant.CAB)
@CrossOrigin(CabConstant.CROSS_ORIGIN)
public class CabController {

	@Autowired
	private CabProxyController cabProxy;

	@GetMapping(CabConstant.GET_CAB_BY_CAB_ID)
	public ResponseEntity<List<Cab>> getAllCab() {
		log.info("getAllCab controller called");
		return cabProxy.getAllCab();
	}

	@GetMapping(CabConstant.ADD_SEATS_BY_CAB_ID)
	public ResponseEntity<Optional<Cab>> getCabByCabId(@PathVariable long id) {
		log.info("getCabByCabId controller called");
		return cabProxy.getCabByCabId(id);
	}

	@PostMapping(CabConstant.SAVE_CAB)
	public ResponseEntity<Cab> saveCab(@RequestBody Cab Cab) {
		log.info("saveCab controller called");
		return cabProxy.saveCab(Cab);
	}

	@PostMapping(CabConstant.BOOK_CAB_BY_CAB_ID)
	public ResponseEntity<CabBookingDetails> bookCabByCabId(@PathVariable long id, @PathVariable String username,
			@RequestBody BookingRequest bookingRequest) {
		log.info("bookCabByCabId controller called");
		return cabProxy.bookCabByCabId(id, username, bookingRequest);
	}

	@PostMapping(CabConstant.BOOK_RENTAL_CAB_BY_CAB_ID)
	public ResponseEntity<RentalCabBookingDetails> bookRentalCabByRentalCabId(@PathVariable long id,
			@PathVariable String username, @RequestBody BookingRequest bookingRequest) {
		log.info("bookRentalCabByRentalCabId controller called");
		return cabProxy.bookRentalCabByRentalCabId(id, username, bookingRequest);
	}

	@GetMapping(CabConstant.GET_CAB_BOOKING_DETAILS_BY_CAB_BOOKING_ID)
	public ResponseEntity<CabBookingDetails> getCabBookingDetailsByCabBookingId(@PathVariable long id) {
		log.info("getCabBookingDetailsByCabBookingId controller called");
		return cabProxy.getCabBookingDetailsByCabBookingId(id);
	}

	@GetMapping(CabConstant.GET_CAB_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<CabBookingDetails>> getCabBookingDetailsByUsername(@PathVariable String username) {
		log.info("getCabBookingDetailsByUsername controller called");
		return cabProxy.getCabBookingDetailsByUsername(username);
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_RENTAL_CAB_BOOKING_ID)
	public ResponseEntity<RentalCabBookingDetails> getRentalCabBookingDetailsByRentalCabBookingId(
			@PathVariable long id) {
		log.info("getRentalCabBookingDetailsByRentalCabBookingId controller called");
		return cabProxy.getRentalCabBookingDetailsByRentalCabBookingId(id);
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<RentalCabBookingDetails>> getRentalCabBookingDetailsByUsername(
			@PathVariable String username) {
		log.info("getRentalCabBookingDetailsByUsername controller called");
		return cabProxy.getRentalCabBookingDetailsByUsername(username);
	}

	@PutMapping(CabConstant.UPDATE_CAB_BY_CAB_ID)
	public ResponseEntity<Cab> updateCabByCabId(@PathVariable long id, @RequestBody Cab Cab) {
		log.info("updateCabByCabId controller called");
		return cabProxy.updateCabByCabId(id, Cab);
	}

	@DeleteMapping(CabConstant.DELETE_CAB_BY_CAB_ID)
	public ResponseEntity<String> deleteCabByCabId(@PathVariable long id) {
		log.info("deleteCabByCabId controller called");
		return cabProxy.deleteCabByCabId(id);
	}

	@PutMapping(CabConstant.CANCEL_PAYMENT_BY_CAB_BOOKING_ID)
	public ResponseEntity<CabBookingDetails> cancelPaymentByCabBookingId(@PathVariable long id) {
		log.info("cancelPaymentByCabBookingId controller called");
		return cabProxy.cancelPaymentByCabBookingId(id);
	}

	@PutMapping(CabConstant.CANCEL_PAYMENT_BY_RENTAL_CAB_BOOKING_ID)
	public ResponseEntity<RentalCabBookingDetails> cancelPaymentByRentalCabBookingId(@PathVariable long id) {
		log.info("cancelPaymentByRentalCabBookingId controller called");
		return cabProxy.cancelPaymentByRentalCabBookingId(id);
	}

	@GetMapping(CabConstant.GET_ALL_CITY_NAMES)
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		log.info("getAllCityNames controller called");
		return cabProxy.getAllCityNames();
	}

	@GetMapping(CabConstant.PAYMENT_STATUS_CHANGE_BY_CAB_BOOKING_ID)
	public ResponseEntity<CabBookingDetails> paymentStatusChangeByCabBookingId(@PathVariable long bookingid) {
		log.info("paymentStatusChangeByCabBookingId controller called");
		return cabProxy.paymentStatusChangeByCabBookingId(bookingid);
	}

	@GetMapping(CabConstant.PAYMENT_STATUS_CHANGE_BY_RENTAL_CAB_BOOKING_ID)
	public ResponseEntity<RentalCabBookingDetails> paymentStatusChangeByRentalCabBookingId(
			@PathVariable long bookingid) {
		log.info("paymentStatusChangeByRentalCabBookingId controller called");
		return cabProxy.paymentStatusChangeByRentalCabBookingId(bookingid);
	}

	@GetMapping(CabConstant.GET_CAB_DETAILS_AND_TRIP_DETAILS)
	public ResponseEntity<CabDetailsTripDetails> getCabDetailsAndTripDetails(@RequestParam String from,
			@RequestParam String to) {
		log.info("getCabDetailsAndTripDetails controller called");
		return cabProxy.getCabDetailsAndTripDetails(from, to);
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_AND_RENTAL_PACKAGE_DETAILS)
	public ResponseEntity<RentalCabsRentalPackageDetails> getRentalCabAndRentalPackageDetails(@RequestParam String from,
			@RequestParam String packageName) {
		log.info("getRentalCabAndRentalPackageDetails controller called");
		return cabProxy.getRentalCabAndRentalPackageDetails(from, packageName);
	}

	@PostMapping(CabConstant.SAVE_TRIP)
	public ResponseEntity<TripDetails> saveTrip(@RequestBody TripDetails trip) {
		log.info("saveTrip controller called");
		return cabProxy.saveTrip(trip);
	}

	@PutMapping(CabConstant.UPDATE_TRIP_BY_TRIP_ID)
	public ResponseEntity<TripDetails> updateTripByTripId(@PathVariable int tripId, @RequestBody TripDetails trip) {
		log.info("updateTripByTripId controller called");
		return cabProxy.updateTripByTripId(tripId, trip);
	}

	@DeleteMapping(CabConstant.DELETE_TRIP_BY_TRIP_ID)
	public ResponseEntity<String> deleteTripByTripId(@PathVariable int tripId) {
		log.info("deleteTripByTripId controller called");
		return cabProxy.deleteTripByTripId(tripId);
	}

	@GetMapping(CabConstant.GET_ALL_TRIP)
	public ResponseEntity<List<TripDetails>> getAllTrip() {
		log.info("getAllTrip controller called");
		return cabProxy.getAllTrip();
	}

	@GetMapping(CabConstant.GET_TRIP_BY_TRIP_ID)
	public ResponseEntity<Optional<TripDetails>> getTripByTripId(@PathVariable int tripId) {
		log.info("getTripByTripId controller called");
		return cabProxy.getTripByTripId(tripId);
	}

	@PostMapping(CabConstant.SAVE_RENTAL_CAB)
	public ResponseEntity<RentalCab> saveRentalCab(@RequestBody RentalCab rentalCab) {
		log.info("saveRentalCab controller called");
		return cabProxy.saveRentalCab(rentalCab);
	}

	@PutMapping(CabConstant.UPDATE_RENTAL_CAB_BY_RENTAL_CAB_ID)
	public ResponseEntity<RentalCab> updateRentalCabByRentalCabId(@PathVariable long rentalCabId,
			@RequestBody RentalCab rentalCab) {
		log.info("updateRentalCabByRentalCabId controller called");
		return cabProxy.updateRentalCabByRentalCabId(rentalCabId, rentalCab);
	}

	@DeleteMapping(CabConstant.DELETE_RENTAL_CAB_BY_RENTAL_CAB_ID)
	public ResponseEntity<String> deleteRentalCabByRentalCabId(@PathVariable long rentalCabId) {
		log.info("deleteRentalCabByRentalCabId controller called");
		return cabProxy.deleteRentalCabByRentalCabId(rentalCabId);
	}

	@GetMapping(CabConstant.GET_ALL_RENTAL_CAB)
	public ResponseEntity<List<RentalCab>> getAllRentalCab() {
		log.info("getAllRentalCab controller called");
		return cabProxy.getAllRentalCab();
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_BY_RENTAL_CAB_ID)
	public ResponseEntity<Optional<RentalCab>> getRentalCabByRentalCabId(@PathVariable long rentalCabId) {
		log.info("getRentalCabByRentalCabId controller called");
		return cabProxy.getRentalCabByRentalCabId(rentalCabId);
	}

	@PostMapping(CabConstant.SAVE_RENTAL_PACKAGE)
	public ResponseEntity<RentalPackage> saveRentalPackage(@RequestBody RentalPackage rentalPackage) {
		log.info("saveRentalPackage controller called");
		return cabProxy.saveRentalPackage(rentalPackage);
	}

	@GetMapping(CabConstant.GET_ALL_RENTAL_CITY_NAMES)
	public ResponseEntity<List<String>> getAllRentalCityNames() {
		log.info("getAllRentalCityNames controller called");
		return cabProxy.getAllRentalCityNames();
	}

	@PutMapping(CabConstant.UPDATE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	public ResponseEntity<RentalPackage> updateRentalPackageByRentalPackageId(@PathVariable int rentalPackageId,
			@RequestBody RentalPackage rentalPackage) {
		log.info("updateRentalPackageByRentalPackageId controller called");
		return cabProxy.updateRentalPackageByRentalPackageId(rentalPackageId, rentalPackage);
	}

	@DeleteMapping(CabConstant.DELETE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	public ResponseEntity<String> deleteRentalPackageByRentalPackageId(@PathVariable int rentalPackageId) {
		log.info("deleteRentalPackageByRentalPackageId controller called");
		return cabProxy.deleteRentalPackageByRentalPackageId(rentalPackageId);
	}

	@GetMapping(CabConstant.GET_ALL_RENTAL_PACKAGE)
	public ResponseEntity<List<RentalPackage>> getAllRentalPackage() {
		log.info("getAllRentalPackage controller called");
		return cabProxy.getAllRentalPackage();
	}

	@GetMapping(CabConstant.GET_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	public ResponseEntity<Optional<RentalPackage>> getRentalPackageByRentalPackageId(
			@PathVariable int rentalPackageId) {
		log.info("getRentalPackageByRentalPackageId controller called");
		return cabProxy.getRentalPackageByRentalPackageId(rentalPackageId);
	}
}
