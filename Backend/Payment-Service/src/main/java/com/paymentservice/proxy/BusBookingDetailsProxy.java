package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.externalclass.BusBookingDetails;

@FeignClient(name = "BUS-SERVICE", url = "http://localhost:8087/Bus")
public interface BusBookingDetailsProxy {

	@GetMapping("/getbusbookingdetailsbyid/{id}")
	public BusBookingDetails getBusBookingDetailsById(@PathVariable long id);

	@GetMapping("/paymentstatuschange/{bookingid}")
	public BusBookingDetails paymentstatuschange(@PathVariable long bookingid);
	
	@PutMapping("/resetstatus/{busId}")
	public BusBookingDetails resetstatus(@PathVariable long busId) ;
}
