package com.authservice.proxyentity.bus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BUS-SERVICE", url = "http://localhost:8087/Bus")
public interface BusProxyController {
	@GetMapping("/getall")
	public ResponseEntity<List<Bus>> getAllBus();

	@GetMapping("/getbyid/{busId}")
	public ResponseEntity<Optional<Bus>> getBusBybusId(@PathVariable long busId);

	@PutMapping("/addseats/{busId}")
	public ResponseEntity<Bus> addseat(@PathVariable long busId);

	@PostMapping("/save")
	public ResponseEntity<Bus> createBus(@RequestBody Bus Bus);

	@PostMapping("/bookBus/{id}/{username}/{pickUpPoint}/{dropPoint}")
	public ResponseEntity<BusBookingDetails> bookBus(@PathVariable long id,
			@RequestBody BusTravellerBusSeats btbs, @PathVariable String username,@PathVariable String pickUpPoint,@PathVariable
			String dropPoint);

	@GetMapping("/getbusbookingdetailsbyid/{id}")
	public ResponseEntity<BusBookingDetails> getBusBookingDetailsById(@PathVariable long id);

	@GetMapping("/getBusbookingdetailsbyusername/{username}")
	public ResponseEntity<List<BusBookingDetails>> getBusBookingDetailsByUsername(@PathVariable String username);

	@PutMapping("/update/{busId}")
	public ResponseEntity<Bus> updateBus(@PathVariable long busId, @RequestBody Bus Bus);

	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<String> deleteBus(@PathVariable long busId);

	@PutMapping("/resetstatus/{busId}")
	public ResponseEntity<BusBookingDetails> resetstatus(@PathVariable long busId);

	@GetMapping("/getallcitynames")
	public ResponseEntity<List<List<String>>> getAllCityNames();

	@GetMapping("/paymentstatuschange/{bookingid}")
	public ResponseEntity<BusBookingDetails> paymentstatuschange(@PathVariable long bookingid);

	@GetMapping("/getallavailablebuses/{from}/{to}/{departure}")
	public ResponseEntity<List<Bus>> getAllAvailableFlights(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure);
}
