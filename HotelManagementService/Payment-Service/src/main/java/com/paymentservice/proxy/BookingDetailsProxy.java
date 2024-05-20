package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.paymentservice.externalclass.BookingDetails;

@FeignClient(name = "bookingdetails-service", url = "http://localhost:8084/Bookindetails")
public interface BookingDetailsProxy {

	@GetMapping("/getbyid/{bookingid}")
	public BookingDetails getBookingDetails(@PathVariable Integer booking_id);

	@GetMapping("/paymentstatuschangebybid/{bookingid}")
	public BookingDetails paymentstatuschange(@PathVariable Integer booking_id);
}
