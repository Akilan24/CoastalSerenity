package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.externalclass.HotelBookingDetails;

@FeignClient(name = "HOTEL-SERVICE", url = "http://localhost:8082/Hotel")
public interface HotelBookingDetailsProxy {

	@GetMapping("/HotelBookingDetails/getbyid/{bookingid}")
	public HotelBookingDetails getBookingDetails(@PathVariable long bookingid);

	@PutMapping("/HotelBookingDetails/paymentstatuschangebybid/{bookingid}")
	public HotelBookingDetails paymentstatuschange(@PathVariable long bookingid);
	
	@PutMapping("/HotelBookingDetails/resetstatus/{id}")
	public ResponseEntity<HotelBookingDetails> resetstatus(@PathVariable long id);
}
