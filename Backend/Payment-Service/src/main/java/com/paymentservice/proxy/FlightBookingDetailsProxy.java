package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.constant.FlightProxyConstant;
import com.paymentservice.externalclass.FlightBookingDetails;

@FeignClient(name = FlightProxyConstant.SERVICE, url = FlightProxyConstant.URL)
public interface FlightBookingDetailsProxy {

	@GetMapping(FlightProxyConstant.GET_FLIGHT_BOOKING_DETAILS_BY_FLIGHT_BOOKING_ID)
	public FlightBookingDetails getFlightBookingDetailsById(@PathVariable long id);

	@GetMapping(FlightProxyConstant.PAYMENT_STATUS_CHANGE_BY_FLIGHT_BOOKING_ID)
	public ResponseEntity<FlightBookingDetails> paymentstatuschange(@PathVariable long bookingid);

	@PutMapping(FlightProxyConstant.CANCEL_PAYMENT_BY_FLIGHT_BOOKING_ID)
	public FlightBookingDetails resetstatus(@PathVariable long id);
}
