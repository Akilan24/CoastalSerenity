package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.externalclass.FlightBookingDetails;

@FeignClient(name = "FLIGHT-SERVICE", url = "http://localhost:8086/Flight")
public interface FlightBookingDetailsProxy {

	@GetMapping("/getflightbookingdetailsbyid/{id}")
	public FlightBookingDetails getFlightBookingDetailsById(@PathVariable long id);

	@GetMapping("/paymentstatuschange/{bookingid}")
	public ResponseEntity<FlightBookingDetails> paymentstatuschange(@PathVariable long bookingid);
	
	@PutMapping("/resetstatus/{id}")
	public FlightBookingDetails resetstatus(@PathVariable long id);
}
