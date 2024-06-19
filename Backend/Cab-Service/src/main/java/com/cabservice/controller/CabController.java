package com.cabservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.constant.CabConstant;
import com.cabservice.entity.BookingRequest;
import com.cabservice.entity.Cab;
import com.cabservice.entity.CabBookingDetails;
import com.cabservice.entity.CabDetailsTripDetails;
import com.cabservice.entity.RentalCab;
import com.cabservice.entity.RentalCabBookingDetails;
import com.cabservice.entity.RentalCabsRentalPackageDetails;
import com.cabservice.entity.RentalPackage;
import com.cabservice.entity.TripDetails;
import com.cabservice.service.CabService;

@RestController
@RequestMapping(CabConstant.CAB)
public class CabController {

	@Autowired
	private CabService cabService;

	@GetMapping(CabConstant.GET_CAB_BY_CAB_ID)
	public ResponseEntity<List<Cab>> getAllCabs() {
		return new ResponseEntity<>(cabService.getAllCabs(), HttpStatus.OK);
	}

	@GetMapping(CabConstant.ADD_SEATS_BY_CAB_ID)
	public ResponseEntity<Optional<Cab>> getCabByCabId(@PathVariable long id) {
		return new ResponseEntity<>(cabService.getCabByCabId(id), HttpStatus.OK);
	}

	@PostMapping(CabConstant.SAVE_CAB)
	public ResponseEntity<Cab> saveCab(@RequestBody Cab Cab) {
		return new ResponseEntity<>(cabService.saveCab(Cab), HttpStatus.CREATED);
	}

	@PostMapping(CabConstant.BOOK_CAB_BY_CAB_ID)
	public ResponseEntity<CabBookingDetails> bookCabByCabId(@PathVariable long id, @PathVariable String username,
			@RequestBody BookingRequest bookingRequest) {
		return new ResponseEntity<>(cabService.bookCabByCabId(id, username, bookingRequest), HttpStatus.CREATED);
	}

	@PostMapping(CabConstant.BOOK_RENTAL_CAB_BY_CAB_ID)
	public ResponseEntity<RentalCabBookingDetails> bookRentalCabByRentalCabId(@PathVariable long id, @PathVariable String username,
			@RequestBody BookingRequest bookingRequest) {
		return new ResponseEntity<>(cabService.bookRentalCabByRentalCabId(id, username, bookingRequest), HttpStatus.CREATED);
	}

	@GetMapping(CabConstant.GET_CAB_BOOKING_DETAILS_BY_CAB_BOOKING_ID)
	public ResponseEntity<CabBookingDetails> getCabBookingDetailsByCabBookingId(@PathVariable long id) {
		return new ResponseEntity<>(cabService.getCabBookingDetailsByCabBookingId(id), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_CAB_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<CabBookingDetails>> getCabBookingDetailsByUsername(@PathVariable String username) {
		return new ResponseEntity<>(cabService.getCabBookingDetailsByUsername(username), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_RENTAL_CAB_BOOKING_ID)
	public ResponseEntity<RentalCabBookingDetails> getRentalCabBookingDetailsByRentalCabBookingId(@PathVariable long id) {
		return new ResponseEntity<>(cabService.getRentalCabBookingDetailsByRentalCabBookingId(id), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<RentalCabBookingDetails>> getRentalCabBookingDetailsByUsername(
			@PathVariable String username) {
		return new ResponseEntity<>(cabService.getRentalCabBookingDetailsByUsername(username), HttpStatus.OK);
	}

	@PutMapping(CabConstant.UPDATE_CAB_BY_CAB_ID)
	public ResponseEntity<Cab> updateCabByCabId(@PathVariable long id, @RequestBody Cab Cab) {
		return new ResponseEntity<>(cabService.updateCabByCabId(id, Cab), HttpStatus.OK);
	}

	@DeleteMapping(CabConstant.DELETE_CAB_BY_CAB_ID)
	public ResponseEntity<String> deleteCabByCabId(@PathVariable long id) {
		return new ResponseEntity<>(cabService.deleteCabByCabId(id), HttpStatus.OK);
	}

	@PutMapping(CabConstant.CANCEL_PAYMENT_BY_CAB_BOOKING_ID)
	public ResponseEntity<CabBookingDetails> cancelPaymentByCabBookingId(@PathVariable long id) {
		return new ResponseEntity<>(cabService.cancelPaymentByCabBookingId(id), HttpStatus.OK);
	}

	@PutMapping(CabConstant.CANCEL_PAYMENT_BY_RENTAL_CAB_BOOKING_ID)
	public ResponseEntity<RentalCabBookingDetails> cancelPaymentByRentalCabBookingId(@PathVariable long id) {
		return new ResponseEntity<>(cabService.cancelPaymentByRentalCabBookingId(id), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_ALL_CITY_NAMES)
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return new ResponseEntity<>(cabService.getAllCityNames(), HttpStatus.OK);
	}

	@GetMapping(CabConstant.PAYMENT_STATUS_CHANGE_BY_CAB_BOOKING_ID)
	public ResponseEntity<CabBookingDetails> paymentStatusChangeByCabBookingId(@PathVariable long bookingid) {
		return new ResponseEntity<>(cabService.paymentStatusChangeByCabBookingId(bookingid), HttpStatus.OK);
	}

	@GetMapping(CabConstant.PAYMENT_STATUS_CHANGE_BY_RENTAL_CAB_BOOKING_ID)
	public ResponseEntity<RentalCabBookingDetails> paymentStatusChangeByRentalCabBookingId(@PathVariable long bookingid) {
		return new ResponseEntity<>(cabService.paymentStatusChangeByRentalCabBookingId(bookingid), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_CAB_DETAILS_AND_TRIP_DETAILS)
	public ResponseEntity<CabDetailsTripDetails> getCabDetailsAndTripDetails(@RequestParam String from,
			@RequestParam String to) {
		return new ResponseEntity<>(cabService.getCabDetailsAndTripDetails(from, to), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_AND_RENTAL_PACKAGE_DETAILS)
	public ResponseEntity<RentalCabsRentalPackageDetails> getRentalCabAndRentalPackageDetails(@RequestParam String from,
			@RequestParam String packageName) {
		return new ResponseEntity<>(cabService.getRentalCabAndRentalPackageDetails(from, packageName), HttpStatus.OK);
	}

	@PostMapping(CabConstant.SAVE_TRIP)
	public ResponseEntity<TripDetails> saveTrip(@RequestBody TripDetails trip) {
		return new ResponseEntity<>(cabService.saveTrip(trip), HttpStatus.CREATED);
	}

	@PutMapping(CabConstant.UPDATE_TRIP_BY_TRIP_ID)
	public ResponseEntity<TripDetails> updateTripByTripId(@PathVariable int tripId, @RequestBody TripDetails trip) {
		return new ResponseEntity<>(cabService.updateTripByTripId(tripId, trip), HttpStatus.OK);
	}

	@DeleteMapping(CabConstant.DELETE_TRIP_BY_TRIP_ID)
	public ResponseEntity<String> deleteTripByTripId(@PathVariable int tripId) {
		return new ResponseEntity<>(cabService.deleteTripByTripId(tripId), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_ALL_TRIP)
	public ResponseEntity<List<TripDetails>> getAllTrip() {
		return new ResponseEntity<>(cabService.getAllTrips(), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_TRIP_BY_TRIP_ID)
	public ResponseEntity<Optional<TripDetails>> getTripByTripId(@PathVariable int tripId) {
		return new ResponseEntity<>(cabService.getTripByTripId(tripId), HttpStatus.OK);
	}

	@PostMapping(CabConstant.SAVE_RENTAL_CAB)
	public ResponseEntity<RentalCab> saveRentalCab(@RequestBody RentalCab rentalCab) {
		return new ResponseEntity<>(cabService.saveRentalCab(rentalCab), HttpStatus.CREATED);
	}

	@PutMapping(CabConstant.UPDATE_RENTAL_CAB_BY_RENTAL_CAB_ID)
	public ResponseEntity<RentalCab> updateRentalCabByRentalCabId(@PathVariable long rentalCabId, @RequestBody RentalCab rentalCab) {
		return new ResponseEntity<>(cabService.updateRentalCabByRentalCabId(rentalCabId, rentalCab), HttpStatus.OK);
	}

	@DeleteMapping(CabConstant.DELETE_RENTAL_CAB_BY_RENTAL_CAB_ID)
	public ResponseEntity<String> deleteRentalCabByRentalCabId(@PathVariable long rentalCabId) {
		return new ResponseEntity<>(cabService.deleteRentalCabByRentalCabId(rentalCabId), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_ALL_RENTAL_CAB)
	public ResponseEntity<List<RentalCab>> getAllRentalCab() {
		return new ResponseEntity<>(cabService.getAllRentalCab(), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_RENTAL_CAB_BY_RENTAL_CAB_ID)
	public ResponseEntity<Optional<RentalCab>> getRentalCabByRentalCabId(@PathVariable long rentalCabId) {
		return new ResponseEntity<>(cabService.getRentalCabByRentalCabId(rentalCabId), HttpStatus.OK);
	}

	@PostMapping(CabConstant.SAVE_RENTAL_PACKAGE)
	public ResponseEntity<RentalPackage> saveRentalPackage(@RequestBody RentalPackage rentalPackage) {
		return new ResponseEntity<>(cabService.saveRentalPackage(rentalPackage), HttpStatus.CREATED);
	}

	@GetMapping(CabConstant.GET_ALL_RENTAL_CITY_NAMES)
	public ResponseEntity<List<String>> getAllRentalCityNames() {
		return new ResponseEntity<>(cabService.getAllRentalCityNames(), HttpStatus.OK);
	}

	@PutMapping(CabConstant.UPDATE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	public ResponseEntity<RentalPackage> updateRentalPackageByRentalPackageId(@PathVariable int rentalPackageId,
			@RequestBody RentalPackage rentalPackage) {
		return new ResponseEntity<>(cabService.updateRentalPackageByRentalPackageId(rentalPackageId, rentalPackage), HttpStatus.OK);
	}

	@DeleteMapping(CabConstant.DELETE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	public ResponseEntity<String> deleteRentalPackageByRentalPackageId(@PathVariable int rentalPackageId) {
		return new ResponseEntity<>(cabService.deleteRentalPackageByRentalPackageId(rentalPackageId), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_ALL_RENTAL_PACKAGE)
	public ResponseEntity<List<RentalPackage>> getAllRentalPackage() {
		return new ResponseEntity<>(cabService.getAllRentalPackage(), HttpStatus.OK);
	}

	@GetMapping(CabConstant.GET_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID)
	public ResponseEntity<Optional<RentalPackage>> getRentalPackageByRentalPackageId(@PathVariable int rentalPackageId) {
		return new ResponseEntity<>(cabService.getRentalPackageByByRentalPackageId(rentalPackageId), HttpStatus.OK);
	}
}
