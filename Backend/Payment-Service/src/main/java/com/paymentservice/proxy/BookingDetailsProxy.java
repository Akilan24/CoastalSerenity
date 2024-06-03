package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.externalclass.BookingDetails;

@FeignClient(name = "bookingdetails-service", url = "http://localhost:8084/BookingDetails")
public interface BookingDetailsProxy {

	@GetMapping("/getbyid/{bookingid}")
	public BookingDetails getBookingDetails(@PathVariable long bookingid);

	@PutMapping("/paymentstatuschangebybid/{bookingid}")
	public BookingDetails paymentstatuschange(@PathVariable long bookingid);
}
