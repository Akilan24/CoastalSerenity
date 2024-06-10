package com.authservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.proxyentity.bus.Bus;
import com.authservice.proxyentity.bus.BusBookingDetails;
import com.authservice.proxyentity.bus.BusProxyController;
import com.authservice.proxyentity.bus.BusTravellerBusSeats;

@RestController
@RequestMapping("/CS/Bus")
@CrossOrigin("http://localhost:5173")
public class BusController {

	@Autowired
	private BusProxyController busProxy;

	@GetMapping("/getall")
	public ResponseEntity<List<Bus>> getAllBus() {
		return busProxy.getAllBus();
	}

	@GetMapping("/getbyid/{busId}")
	public ResponseEntity<Optional<Bus>> getBusBybusId(@PathVariable long busId) {
		return busProxy.getBusBybusId(busId);
	}

	@PutMapping("/addseats/{busId}")
	public ResponseEntity<Bus> addseat(@PathVariable long busId) {
		return busProxy.addseat(busId);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Bus> createBus(@RequestBody Bus Bus) {
		return busProxy.createBus(Bus);
	}

	@PostMapping("/bookBus/{id}/{username}")
	public ResponseEntity<BusBookingDetails> bookBus(@PathVariable long id,
			@RequestBody BusTravellerBusSeats btbs, @PathVariable String username) {
		return busProxy.bookBus(id, btbs, username);
	}
	
	@GetMapping("/getbusbookingdetailsbyid/{id}")
	public ResponseEntity<BusBookingDetails> getBusBookingDetailsById(@PathVariable long id) {
		return busProxy.getBusBookingDetailsById(id);
	}
	
	@GetMapping("/getBusbookingdetailsbyusername/{username}")
	public ResponseEntity<List<BusBookingDetails>> getBusBookingDetailsByUsername(@PathVariable String username) {
		return busProxy.getBusBookingDetailsByUsername(username);
	}
	
	@PutMapping("/update/{busId}")
	public ResponseEntity<Bus> updateBus(@PathVariable long busId, @RequestBody Bus Bus) {
		return busProxy.updateBus(busId, Bus);
	}

	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<String> deleteBus(@PathVariable long busId) {
		return busProxy.deleteBus(busId);

	}

	@PutMapping("/resetstatus/{busId}")
	public ResponseEntity<BusBookingDetails> resetstatus(@PathVariable long busId) {
		return busProxy.resetstatus(busId);
	}
	
	@GetMapping("/getallcitynames")
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return busProxy.getAllCityNames();
	}
	
	@GetMapping("/paymentstatuschange/{bookingid}")
	public ResponseEntity<BusBookingDetails> paymentstatuschange(@PathVariable long bookingid) {
		return busProxy.paymentstatuschange(bookingid);
	}

	@GetMapping("/getallavailablebuses/{from}/{to}/{departure}")
	public ResponseEntity<List<Bus>> getAllAvailableFlights(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure) {
		return busProxy.getAllAvailableFlights(from, to, departure);
	}
}
