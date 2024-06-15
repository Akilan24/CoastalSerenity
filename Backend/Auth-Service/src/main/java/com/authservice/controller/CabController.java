package com.authservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.authservice.proxyentity.cab.BookingRequest;
import com.authservice.proxyentity.cab.Cab;
import com.authservice.proxyentity.cab.CabBookingDetails;
import com.authservice.proxyentity.cab.CabDetailsTripDetails;
import com.authservice.proxyentity.cab.CabProxyController;
import com.authservice.proxyentity.cab.RentalCab;
import com.authservice.proxyentity.cab.RentalCabsRentalPackageDetails;
import com.authservice.proxyentity.cab.RentalPackage;
import com.authservice.proxyentity.cab.TripDetails;

@RestController
@RequestMapping("/CS/Cab")
@CrossOrigin("http://localhost:5173")
public class CabController {

	@Autowired
	private CabProxyController cabProxy;

	 @GetMapping("/getall")
	    ResponseEntity<List<Cab>> getAllCabs(){
		return cabProxy.getAllCabs();
	 }

	    @GetMapping("/getbyid/{id}")
	    ResponseEntity<Optional<Cab>> getCabById(@PathVariable("id") long id){
	    	return cabProxy.getCabById(id);
	    }

	    @PostMapping("/save")
	    ResponseEntity<Cab> createCab(@RequestBody Cab cab){
	    	return cabProxy.createCab(cab);
	    }

	    @PostMapping("/bookCab/{id}/{username}")
		public ResponseEntity<CabBookingDetails> bookCab(@PathVariable long id,@PathVariable String username,@RequestBody BookingRequest bookingRequest) {
			return cabProxy.bookCab(id, username,bookingRequest);
		}

	    @GetMapping("/getCabbookingdetailsbyid/{id}")
	    ResponseEntity<CabBookingDetails> getCabBookingDetailsById(@PathVariable("id") long id){
	    	return cabProxy.getCabBookingDetailsById(id);
	    }

	    @GetMapping("/getCabbookingdetailsbyusername/{username}")
	    ResponseEntity<List<CabBookingDetails>> getCabBookingDetailsByUsername(@PathVariable("username") String username){
	    	return cabProxy.getCabBookingDetailsByUsername(username);
	    }

	    @PutMapping("/update/{id}")
	    ResponseEntity<Cab> updateCab(@PathVariable("id") long id, @RequestBody Cab cab){
	    	return cabProxy.updateCab(id, cab);
	    }

	    @DeleteMapping("/delete/{id}")
	    ResponseEntity<String> deleteCab(@PathVariable("id") long id){
	    	return cabProxy.deleteCab(id);
	    }

	    @PutMapping("/resetstatus/{id}")
	    ResponseEntity<CabBookingDetails> resetstatus(@PathVariable("id") long id){
	    	return cabProxy.resetstatus(id);
	    }

	    @GetMapping("/getallcitynames")
	    ResponseEntity<List<List<String>>> getAllCityNames(){
	    	return cabProxy.getAllCityNames();
	    }

	    @GetMapping("/paymentstatuschange/{bookingid}")
	    ResponseEntity<CabBookingDetails> paymentstatuschange(@PathVariable("bookingid") long bookingid){
	    	return cabProxy.paymentstatuschange(bookingid);
	    }
	    
	    @GetMapping("/getCabDetailsAndTripDetails")
		public ResponseEntity<CabDetailsTripDetails> getCabDetailsAndTripDetails(@RequestParam String from, @RequestParam String to){
	    	return cabProxy.getCabDetailsAndTripDetails(from, to);
	    }

	    @GetMapping("/getRentalCabAndRentalPackageDetails")
		public ResponseEntity<RentalCabsRentalPackageDetails> getRentalCabAndRentalPackageDetails(@RequestParam String from) {
			return cabProxy.getRentalCabAndRentalPackageDetails(from);
		}
	    
	    @PostMapping("/saveTrip")
	    ResponseEntity<TripDetails> saveTrip(@RequestBody TripDetails trip){
	    	return cabProxy.saveTrip(trip);
	    }

	    @PutMapping("/updateTrip/{tripId}")
	    ResponseEntity<TripDetails> updateTrip(@PathVariable("tripId") int tripId, @RequestBody TripDetails trip){
	    	return cabProxy.updateTrip(tripId, trip);
	    }

	    @DeleteMapping("/deleteTrip/{tripId}")
	    ResponseEntity<String> deleteTrip(@PathVariable("tripId") int tripId){
	    	return cabProxy.deleteTrip(tripId);
	    }

	    @GetMapping("/getAllTrip")
	    ResponseEntity<List<TripDetails>> getAllTrip(){
	    	return cabProxy.getAllTrip();
	    }

	    @GetMapping("/getTripbyId/{tripId}")
	    ResponseEntity<Optional<TripDetails>> getTripById(@PathVariable("tripId") int tripId){
	    	return cabProxy.getTripById(tripId);
	    }

	    @PostMapping("/saveRentalCab")
	    ResponseEntity<RentalCab> saveRentalCab(@RequestBody RentalCab rentalCab){
	    	return cabProxy.saveRentalCab(rentalCab);
	    }

	    @PutMapping("/updateRentalCab/{rentalCabId}")
	    ResponseEntity<RentalCab> updateRentalCab(@PathVariable("rentalCabId") int rentalCabId, @RequestBody RentalCab rentalCab){
	    	return cabProxy.updateRentalCab(rentalCabId, rentalCab);
	    }

	    @DeleteMapping("/deleteRentalCab/{rentalCabId}")
	    ResponseEntity<String> deleteRentalCab(@PathVariable("rentalCabId") int rentalCabId){
	    	return cabProxy.deleteRentalCab(rentalCabId);
	    }

	    @GetMapping("/getAllRentalCab")
	    ResponseEntity<List<RentalCab>> getAllRentalCab(){
	    	return cabProxy.getAllRentalCab();
	    }

	    @GetMapping("/getRentalCabbyId/{rentalCabId}")
	    ResponseEntity<Optional<RentalCab>> getRentalCabById(@PathVariable("rentalCabId") int rentalCabId){
	    	return cabProxy.getRentalCabById(rentalCabId);
	    }

	    @PostMapping("/saveRentalPackage")
	    ResponseEntity<RentalPackage> saveRentalPackage(@RequestBody RentalPackage rentalPackage){
	    	return cabProxy.saveRentalPackage(rentalPackage);
	    }

	    @GetMapping("/getallRentalcitynames")
		public ResponseEntity<List<String>> getAllRentalCityNames() {
			return cabProxy.getAllRentalCityNames();
		}
	    
	    @PutMapping("/updateRentalPackage/{rentalPackageId}")
	    ResponseEntity<RentalPackage> updateRentalPackage(@PathVariable("rentalPackageId") int rentalPackageId, @RequestBody RentalPackage rentalPackage){
	    	return cabProxy.updateRentalPackage(rentalPackageId, rentalPackage);
	    }

	    @DeleteMapping("/deleteRentalPackage/{rentalPackageId}")
	    ResponseEntity<String> deleteRentalPackage(@PathVariable("rentalPackageId") int rentalPackageId){
	    	return cabProxy.deleteRentalPackage(rentalPackageId);
	    }

	    @GetMapping("/getAllRentalPackage")
	    ResponseEntity<List<RentalPackage>> getAllRentalPackage(){
	    	return cabProxy.getAllRentalPackage();
	    }

	    @GetMapping("/getRentalPackagebyId/{rentalPackageId}")
	    ResponseEntity<Optional<RentalPackage>> getRentalPackageById(@PathVariable("rentalPackageId") int rentalPackageId){
	    	return cabProxy.getRentalPackageById(rentalPackageId);
	    }
}
