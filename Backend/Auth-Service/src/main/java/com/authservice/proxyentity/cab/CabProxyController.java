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

@FeignClient(name = "CAB-SERVICE", url = "http://localhost:8085/Cab")
public interface CabProxyController {

	@GetMapping("/getall")
	ResponseEntity<List<Cab>> getAllCabs();

	@GetMapping("/getbyid/{id}")
	ResponseEntity<Optional<Cab>> getCabById(@PathVariable("id") long id);

	@PostMapping("/save")
	ResponseEntity<Cab> createCab(@RequestBody Cab cab);

	@PostMapping("/bookCab/{id}/{username}")
	public ResponseEntity<CabBookingDetails> bookCab(@PathVariable long id,@PathVariable String username,@RequestBody BookingRequest bookingRequest);

	@GetMapping("/getCabbookingdetailsbyid/{id}")
	ResponseEntity<CabBookingDetails> getCabBookingDetailsById(@PathVariable("id") long id);

	@GetMapping("/getCabbookingdetailsbyusername/{username}")
	ResponseEntity<List<CabBookingDetails>> getCabBookingDetailsByUsername(@PathVariable("username") String username);

	@PutMapping("/update/{id}")
	ResponseEntity<Cab> updateCab(@PathVariable("id") long id, @RequestBody Cab cab);

	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deleteCab(@PathVariable("id") long id);

	@PutMapping("/resetstatus/{id}")
	ResponseEntity<CabBookingDetails> resetstatus(@PathVariable("id") long id);

	@GetMapping("/getallcitynames")
	ResponseEntity<List<List<String>>> getAllCityNames();

	@GetMapping("/paymentstatuschange/{bookingid}")
	ResponseEntity<CabBookingDetails> paymentstatuschange(@PathVariable("bookingid") long bookingid);

	@PostMapping("/saveTrip")
	ResponseEntity<TripDetails> saveTrip(@RequestBody TripDetails trip);

	@PutMapping("/updateTrip/{tripId}")
	ResponseEntity<TripDetails> updateTrip(@PathVariable("tripId") int tripId, @RequestBody TripDetails trip);

	@DeleteMapping("/deleteTrip/{tripId}")
	ResponseEntity<String> deleteTrip(@PathVariable("tripId") int tripId);

	@GetMapping("/getAllTrip")
	ResponseEntity<List<TripDetails>> getAllTrip();

	@GetMapping("/getTripbyId/{tripId}")
	ResponseEntity<Optional<TripDetails>> getTripById(@PathVariable("tripId") int tripId);

	@PostMapping("/saveRentalCab")
	ResponseEntity<RentalCab> saveRentalCab(@RequestBody RentalCab rentalCab);

	@PutMapping("/updateRentalCab/{rentalCabId}")
	ResponseEntity<RentalCab> updateRentalCab(@PathVariable("rentalCabId") int rentalCabId,
			@RequestBody RentalCab rentalCab);

	@DeleteMapping("/deleteRentalCab/{rentalCabId}")
	ResponseEntity<String> deleteRentalCab(@PathVariable("rentalCabId") int rentalCabId);

	@GetMapping("/getAllRentalCab")
	ResponseEntity<List<RentalCab>> getAllRentalCab();

	@GetMapping("/getRentalCabbyId/{rentalCabId}")
	ResponseEntity<Optional<RentalCab>> getRentalCabById(@PathVariable("rentalCabId") int rentalCabId);

	@PostMapping("/saveRentalPackage")
	ResponseEntity<RentalPackage> saveRentalPackage(@RequestBody RentalPackage rentalPackage);

	@PutMapping("/updateRentalPackage/{rentalPackageId}")
	ResponseEntity<RentalPackage> updateRentalPackage(@PathVariable("rentalPackageId") int rentalPackageId,
			@RequestBody RentalPackage rentalPackage);

	@DeleteMapping("/deleteRentalPackage/{rentalPackageId}")
	ResponseEntity<String> deleteRentalPackage(@PathVariable("rentalPackageId") int rentalPackageId);

	@GetMapping("/getAllRentalPackage")
	ResponseEntity<List<RentalPackage>> getAllRentalPackage();

	@GetMapping("/getRentalPackagebyId/{rentalPackageId}")
	ResponseEntity<Optional<RentalPackage>> getRentalPackageById(@PathVariable("rentalPackageId") int rentalPackageId);
}
