package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.constant.HotelProxyConstant;
import com.paymentservice.externalclass.HotelBookingDetails;

@FeignClient(name = HotelProxyConstant.SERVICE, url = HotelProxyConstant.URL)
public interface HotelBookingDetailsProxy {

	@GetMapping(HotelProxyConstant.GET_HOTEL_BOOKING_DETAILS_BY_HOTEL_BOOKING_ID)
	public HotelBookingDetails getBookingDetails(@PathVariable long bookingid);

	@PutMapping(HotelProxyConstant.PAYMENT_STATUS_CHANGE_BY_HOTEL_BOOKING_ID)
	public HotelBookingDetails paymentstatuschange(@PathVariable long bookingid);

	@PutMapping(HotelProxyConstant.CANCEL_PAYMENT_BY_HOTEL_BOOKING_ID)
	public HotelBookingDetails resetstatus(@PathVariable long id);
}
