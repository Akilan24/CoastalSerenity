package com.busservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busservice.entity.Bus;
import com.busservice.entity.BusBookingDetails;
import com.busservice.entity.BusTravellerBusSeats;
import com.busservice.service.BusService;

@RestController
@RequestMapping("/Bus")
public class BusController {

	@Autowired
	private BusService BusService;

	@GetMapping("/getall")
	public ResponseEntity<List<Bus>> getAllBus() {
		return new ResponseEntity<>(BusService.getAllBus(), HttpStatus.OK);
	}

	@GetMapping("/getbyid/{busId}")
	public ResponseEntity<Optional<Bus>> getBusBybusId(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.getBusById(busId), HttpStatus.OK);
	}

	@PutMapping("/addseats/{busId}")
	public ResponseEntity<Bus> addseat(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.addSeats(busId), HttpStatus.CREATED);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Bus> createBus(@RequestBody Bus Bus) {
		return new ResponseEntity<>(BusService.saveBus(Bus), HttpStatus.CREATED);
	}

	@PostMapping("/bookBus/{id}/{username}/{pickUpPoint}/{dropPoint}")
	public ResponseEntity<BusBookingDetails> bookBus(@PathVariable long id,
			@RequestBody BusTravellerBusSeats btbs, @PathVariable String username,@PathVariable String pickUpPoint,@PathVariable
			String dropPoint) {
		return new ResponseEntity<>(BusService.bookBus(id, btbs, username,pickUpPoint,dropPoint), HttpStatus.CREATED);
	}
	
	@GetMapping("/getbusbookingdetailsbyid/{id}")
	public ResponseEntity<BusBookingDetails> getBusBookingDetailsById(@PathVariable long id) {
		return new ResponseEntity<>(BusService.getBusBookingDetailsById(id), HttpStatus.OK);
	}
	
	@GetMapping("/getBusbookingdetailsbyusername/{username}")
	public ResponseEntity<List<BusBookingDetails>> getBusBookingDetailsByUsername(@PathVariable String username) {
		return new ResponseEntity<>(BusService.getBusBookingDetailsByUsername(username), HttpStatus.OK);
	}
	
	@PutMapping("/update/{busId}")
	public ResponseEntity<Bus> updateBus(@PathVariable long busId, @RequestBody Bus Bus) {
		return new ResponseEntity<>(BusService.updateBus(busId, Bus), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<String> deleteBus(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.deleteBus(busId), HttpStatus.OK);

	}

	@PutMapping("/resetstatus/{busId}")
	public ResponseEntity<BusBookingDetails> resetstatus(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.resetStatus(busId), HttpStatus.OK);
	}
	
	@GetMapping("/getallcitynames")
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return new ResponseEntity<>(BusService.getAllCityNames(), HttpStatus.OK);
	}
	
	@GetMapping("/paymentstatuschange/{bookingid}")
	public ResponseEntity<BusBookingDetails> paymentstatuschange(@PathVariable long bookingid) {
		return new ResponseEntity<>(BusService.paymentstatuschange(bookingid), HttpStatus.OK);
	}

	@GetMapping("/getallavailablebuses/{from}/{to}/{departure}")
	public ResponseEntity<List<Bus>> getAllAvailableFlights(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure) {
		return new ResponseEntity<>(BusService.getAllAvailableBuses(from, to, departure),
				HttpStatus.OK);
	}
}
