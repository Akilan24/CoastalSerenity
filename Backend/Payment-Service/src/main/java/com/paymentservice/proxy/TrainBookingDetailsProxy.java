package com.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.paymentservice.externalclass.TrainBookingDetails;

@FeignClient(name = "TRAIN-SERVICE", url = "http://localhost:8088/Train")
public interface TrainBookingDetailsProxy {

	@GetMapping("/getTrainbookingdetailsbyid/{id}")
	public TrainBookingDetails getTrainBookingDetailsById(@PathVariable long id);

	@GetMapping("/paymentstatuschange/{bookingid}")
	public TrainBookingDetails paymentstatuschange(@PathVariable long bookingid);

	@PutMapping("/resetstatus/{TrainId}")
	public TrainBookingDetails resetstatus(@PathVariable long TrainId);
}
