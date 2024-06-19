package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.constant.TrainProxyConstant;
import com.paymentservice.externalclass.TrainBookingDetails;

@FeignClient(name = TrainProxyConstant.SERVICE, url = TrainProxyConstant.URL)
public interface TrainBookingDetailsProxy {

	@GetMapping(TrainProxyConstant.GET_TRAIN_BOOKING_DETAILS_BY_TRAIN_BOOKING_ID)
	public TrainBookingDetails getTrainBookingDetailsById(@PathVariable long id);

	@GetMapping(TrainProxyConstant.PAYMENT_STATUS_CHANGE_BY_TRAIN_BOOKING_ID)
	public TrainBookingDetails paymentstatuschange(@PathVariable long bookingid);

	@PutMapping(TrainProxyConstant.CANCEL_PAYMENT_BY_TRAIN_BOOKING_ID)
	public TrainBookingDetails resetstatus(@PathVariable long TrainId);
}
