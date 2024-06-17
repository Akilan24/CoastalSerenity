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
@RequestMapping("/Cab")
public class CabController {

	@Autowired
	private CabService cabService;

	@GetMapping("/getall")
	public ResponseEntity<List<Cab>> getAllCabs() {
		return new ResponseEntity<>(cabService.getAllCabs(), HttpStatus.OK);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Optional<Cab>> getCabById(@PathVariable long id) {
		return new ResponseEntity<>(cabService.getCabById(id), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Cab> createCab(@RequestBody Cab Cab) {
		return new ResponseEntity<>(cabService.saveCab(Cab), HttpStatus.CREATED);
	}

	@PostMapping("/bookCab/{id}/{username}")
	public ResponseEntity<CabBookingDetails> bookCab(@PathVariable long id, @PathVariable String username,
			@RequestBody BookingRequest bookingRequest) {
		return new ResponseEntity<>(cabService.bookCab(id, username, bookingRequest), HttpStatus.CREATED);
	}

	@PostMapping("/bookRentalCab/{id}/{username}")
	public ResponseEntity<RentalCabBookingDetails> bookRentalCab(@PathVariable long id, @PathVariable String username,
			@RequestBody BookingRequest bookingRequest) {
		return new ResponseEntity<>(cabService.bookRentalCab(id, username, bookingRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/getCabbookingdetailsbyid/{id}")
	public ResponseEntity<CabBookingDetails> getCabBookingDetailsById(@PathVariable long id) {
		return new ResponseEntity<>(cabService.getCabBookingDetailsById(id), HttpStatus.OK);
	}

	@GetMapping("/getCabbookingdetailsbyusername/{username}")
	public ResponseEntity<List<CabBookingDetails>> getCabBookingDetailsByUsername(@PathVariable String username) {
		return new ResponseEntity<>(cabService.getCabBookingDetailsByUsername(username), HttpStatus.OK);
	}

	@GetMapping("/getRentalCabbookingdetailsbyid/{id}")
	public ResponseEntity<RentalCabBookingDetails> getRentalCabBookingDetailsById(@PathVariable long id) {
		return new ResponseEntity<>(cabService.getRentalCabBookingDetailsById(id), HttpStatus.OK);
	}

	@GetMapping("/getRentalCabbookingdetailsbyusername/{username}")
	public ResponseEntity<List<RentalCabBookingDetails>> getRentalCabBookingDetailsByUsername(@PathVariable String username) {
		return new ResponseEntity<>(cabService.getRentalCabBookingDetailsByUsername(username), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Cab> updateCab(@PathVariable long id, @RequestBody Cab Cab) {
		return new ResponseEntity<>(cabService.updateCab(id, Cab), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCab(@PathVariable long id) {
		return new ResponseEntity<>(cabService.deleteCab(id), HttpStatus.OK);
	}

	@PutMapping("/resetstatusCab/{id}")
	public ResponseEntity<CabBookingDetails> resetstatusCab(@PathVariable long id) {
		return new ResponseEntity<>(cabService.resetStatusCab(id), HttpStatus.OK);
	}

	@PutMapping("/resetstatusRentalCab/{id}")
	public ResponseEntity<RentalCabBookingDetails> resetstatusRentalCab(@PathVariable long id) {
		return new ResponseEntity<>(cabService.resetStatusRentalCab(id), HttpStatus.OK);
	}
	
	@GetMapping("/getallcitynames")
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return new ResponseEntity<>(cabService.getAllCityNames(), HttpStatus.OK);
	}

	@GetMapping("/paymentstatuschangeCab/{bookingid}")
	public ResponseEntity<CabBookingDetails> paymentstatuschangeCab(@PathVariable long bookingid) {
		return new ResponseEntity<>(cabService.paymentstatuschangeCab(bookingid), HttpStatus.OK);
	}

	@GetMapping("/paymentstatuschangRentalCab/{bookingid}")
	public ResponseEntity<RentalCabBookingDetails> paymentstatuschangeRentalCab(@PathVariable long bookingid) {
		return new ResponseEntity<>(cabService.paymentstatuschangeRentalCab(bookingid), HttpStatus.OK);
	}
	
	@GetMapping("/getCabDetailsAndTripDetails")
	public ResponseEntity<CabDetailsTripDetails> getCabDetailsAndTripDetails(@RequestParam String from, @RequestParam String to) {
		return new ResponseEntity<>(cabService.getCabDetailsAndTripDetails(from,to), HttpStatus.OK);
	}

	@GetMapping("/getRentalCabAndRentalPackageDetails")
	public ResponseEntity<RentalCabsRentalPackageDetails> getRentalCabAndRentalPackageDetails(@RequestParam String from,@RequestParam String packageName) {
		return new ResponseEntity<>(cabService.getRentalCabAndRentalPackageDetails(from,packageName), HttpStatus.OK);
	}
	
	@PostMapping("/saveTrip")
	public ResponseEntity<TripDetails> saveTrip(@RequestBody TripDetails trip) {
		return new ResponseEntity<>(cabService.saveTrip(trip), HttpStatus.CREATED);
	}

	@PutMapping("/updateTrip/{tripId}")
	public ResponseEntity<TripDetails> updateTrip(@PathVariable int tripId, @RequestBody TripDetails trip) {
		return new ResponseEntity<>(cabService.updateTrip(tripId, trip), HttpStatus.OK);
	}

	@DeleteMapping("/deleteTrip/{tripId}")
	public ResponseEntity<String> deletTrip(@PathVariable int tripId) {
		return new ResponseEntity<>(cabService.deleteTrip(tripId), HttpStatus.OK);
	}

	@GetMapping("/getAllTrip")
	public ResponseEntity<List<TripDetails>> getAllTrip() {
		return new ResponseEntity<>(cabService.getAllTrips(), HttpStatus.OK);
	}

	@GetMapping("/getTripbyId/{tripId}")
	public ResponseEntity<Optional<TripDetails>> getTripById(@PathVariable int tripId) {
		return new ResponseEntity<>(cabService.getTripDetailById(tripId), HttpStatus.OK);
	}

	@PostMapping("/saveRentalCab")
	public ResponseEntity<RentalCab> saveRentalCab(@RequestBody RentalCab rentalCab) {
		return new ResponseEntity<>(cabService.saveRentalCab(rentalCab), HttpStatus.CREATED);
	}

	@PutMapping("/updateRentalCab/{rentalCabId}")
	public ResponseEntity<RentalCab> updateRentalCab(@PathVariable long rentalCabId, @RequestBody RentalCab rentalCab) {
		return new ResponseEntity<>(cabService.updateRentalCab(rentalCabId, rentalCab), HttpStatus.OK);
	}

	@DeleteMapping("/deleteRentalCab/{rentalCabId}")
	public ResponseEntity<String> deleteRentalCab(@PathVariable long rentalCabId) {
		return new ResponseEntity<>(cabService.deleteRentalCab(rentalCabId), HttpStatus.OK);
	}

	@GetMapping("/getAllRentalCab")
	public ResponseEntity<List<RentalCab>> getAllRentalCab() {
		return new ResponseEntity<>(cabService.getAllRentalCab(), HttpStatus.OK);
	}

	@GetMapping("/getRentalCabbyId/{rentalCabId}")
	public ResponseEntity<Optional<RentalCab>> getRentalCabById(@PathVariable long rentalCabId) {
		return new ResponseEntity<>(cabService.getRentalCabById(rentalCabId), HttpStatus.OK);
	}

	@PostMapping("/saveRentalPackage")
	public ResponseEntity<RentalPackage> saveRentalPackage(@RequestBody RentalPackage rentalPackage) {
		return new ResponseEntity<>(cabService.saveRentalPackage(rentalPackage), HttpStatus.CREATED);
	}

	@GetMapping("/getallRentalcitynames")
	public ResponseEntity<List<String>> getAllRentalCityNames() {
		return new ResponseEntity<>(cabService.getAllRentalCityNames(), HttpStatus.OK);
	}

	@PutMapping("/updateRentalPackage/{rentalPackageId}")
	public ResponseEntity<RentalPackage> updateRentalPackage(@PathVariable int rentalPackageId,
			@RequestBody RentalPackage rentalPackage) {
		return new ResponseEntity<>(cabService.updateRentalPackage(rentalPackageId, rentalPackage), HttpStatus.OK);
	}

	@DeleteMapping("/deleteRentalPackage/{rentalPackageId}")
	public ResponseEntity<String> deleteRentalPackage(@PathVariable int rentalPackageId) {
		return new ResponseEntity<>(cabService.deleteRentalPackage(rentalPackageId), HttpStatus.OK);
	}

	@GetMapping("/getAllRentalPackage")
	public ResponseEntity<List<RentalPackage>> getAllRentalPackage() {
		return new ResponseEntity<>(cabService.getAllRentalPackage(), HttpStatus.OK);
	}

	@GetMapping("/getRentalPackagebyId/{rentalPackageId}")
	public ResponseEntity<Optional<RentalPackage>> getrentalPackageIdById(@PathVariable int rentalPackageId) {
		return new ResponseEntity<>(cabService.getRentalPackageById(rentalPackageId), HttpStatus.OK);
	}
}
