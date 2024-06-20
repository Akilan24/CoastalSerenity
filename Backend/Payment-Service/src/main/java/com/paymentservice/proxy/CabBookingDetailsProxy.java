package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.constant.CabProxyConstant;
import com.paymentservice.externalclass.CabBookingDetails;
import com.paymentservice.externalclass.RentalCabBookingDetails;

@FeignClient(name = CabProxyConstant.SERVICE, url = CabProxyConstant.URL)
public interface CabBookingDetailsProxy {

	@GetMapping(CabProxyConstant.GET_CAB_BOOKING_DETAILS_BY_CAB_BOOKING_ID)
	public CabBookingDetails getCabBookingDetailsById(@PathVariable long id);

	@GetMapping(CabProxyConstant.GET_RENTAL_CAB_BOOKING_DETAILS_BY_RENTAL_CAB_BOOKING_ID)
	public RentalCabBookingDetails getRentalCabBookingDetailsById(@PathVariable long id);

	@GetMapping(CabProxyConstant.PAYMENT_STATUS_CHANGE_BY_CAB_BOOKING_ID)
	public CabBookingDetails paymentstatuschangeCab(@PathVariable long bookingid);

	@GetMapping(CabProxyConstant.PAYMENT_STATUS_CHANGE_BY_RENTAL_CAB_BOOKING_ID)
	public RentalCabBookingDetails paymentstatuschangeRentalCab(@PathVariable long bookingid);

	@PutMapping(CabProxyConstant.CANCEL_PAYMENT_BY_CAB_BOOKING_ID)
	public CabBookingDetails resetstatusCab(@PathVariable long id);

	@PutMapping(CabProxyConstant.CANCEL_PAYMENT_BY_RENTAL_CAB_BOOKING_ID)
	public RentalCabBookingDetails resetstatusRentalCab(@PathVariable long id);
}
