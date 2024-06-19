package com.busservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.busservice.entity.Bus;
import com.busservice.entity.BusBookingDetails;
import com.busservice.entity.BusTravellerBusSeats;

@Service
public interface BusService {
	List<Bus> getAllBus();

	Optional<Bus> getBusByBusId(long id);

	Bus saveBus(Bus Bus);

	BusBookingDetails bookBusByBusId(long id, BusTravellerBusSeats btbs, String username, String pickUpPoint,
			String dropPoint);

	BusBookingDetails paymentStatusChangeByBusBookingId(long bookingid);

	Bus updateBusByBusId(long id, Bus bus);

	String deleteBusByBusId(long id);

	BusBookingDetails cancelPaymentByBusBookingId(long id);

	List<List<String>> getAllCityNames();

	List<Bus> getAllAvailableBuses(String from, String to, Date departure);

	Bus addSeatsByBusId(long id);

	BusBookingDetails getBusBookingDetailsByBusBookingId(long id);

	List<BusBookingDetails> getBusBookingDetailsByUsername(String username);

}
