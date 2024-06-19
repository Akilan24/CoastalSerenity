package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.constant.BusProxyConstant;
import com.paymentservice.externalclass.BusBookingDetails;

@FeignClient(name = BusProxyConstant.SERVICE, url = BusProxyConstant.URL)
public interface BusBookingDetailsProxy {

	@GetMapping(BusProxyConstant.GET_BUS_BOOKING_DETAILS_BY_BUS_BOOKING_ID)
	public BusBookingDetails getBusBookingDetailsById(@PathVariable long id);

	@GetMapping(BusProxyConstant.PAYMENT_STATUS_CHANGE_BY_BUS_BOOKING_ID)
	public BusBookingDetails paymentstatuschange(@PathVariable long bookingid);

	@PutMapping(BusProxyConstant.CANCEL_PAYMENT_BY_BUS_BOOKING_ID)
	public BusBookingDetails resetstatus(@PathVariable long busId);
}
