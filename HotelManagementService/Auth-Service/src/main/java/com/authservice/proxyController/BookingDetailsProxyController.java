package com.authservice.proxyController;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.ProxyEntity.BookingDetails;
import com.authservice.ProxyEntity.Room;

@FeignClient(name = "BOOKINGDETAILS-SERVICE", url = "http://localhost:8084/BookingDetails")
public interface BookingDetailsProxyController {

	@GetMapping("/getall")
	public ResponseEntity<List<BookingDetails>> listBookingDetails();

	@GetMapping("/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<Room>> checkRoomAvailability(@PathVariable String city, @PathVariable String roomtype,
			@PathVariable Date fromDate, @PathVariable Date ToDate);
	@GetMapping("/getbyid/{bookingid}")
	public ResponseEntity<BookingDetails> getBookingDetails(@PathVariable Integer bookingid);

	@PostMapping("/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<BookingDetails> paymentstatuschange(@PathVariable Integer bookingid);

	@PutMapping("/bookroom/{userid}")
	public ResponseEntity<BookingDetails> bookroom(@PathVariable String userid, @RequestBody BookingDetails bd);

	@DeleteMapping("/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable Integer bookingid);
}
