package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.externalclass.CabBookingDetails;
import com.paymentservice.externalclass.RentalCabBookingDetails;

@FeignClient(name = "CAB-SERVICE", url = "http://localhost:8085/Cab")
public interface CabBookingDetailsProxy {

	@GetMapping("/getCabbookingdetailsbyid/{id}")
	public CabBookingDetails getCabBookingDetailsById(@PathVariable long id);

	@GetMapping("/getRentalCabbookingdetailsbyid/{id}")
	public RentalCabBookingDetails getRentalCabBookingDetailsById(@PathVariable long id) ;
	
	@GetMapping("/paymentstatuschangeCab/{bookingid}")
	public CabBookingDetails paymentstatuschangeCab(@PathVariable long bookingid) ;

	@GetMapping("/paymentstatuschangRentalCab/{bookingid}")
	public RentalCabBookingDetails paymentstatuschangeRentalCab(@PathVariable long bookingid);
	
	@PutMapping("/resetstatusCab/{id}")
	public CabBookingDetails resetstatusCab(@PathVariable long id) ;

	@PutMapping("/resetstatusRentalCab/{id}")
	public RentalCabBookingDetails resetstatusRentalCab(@PathVariable long id);
}
