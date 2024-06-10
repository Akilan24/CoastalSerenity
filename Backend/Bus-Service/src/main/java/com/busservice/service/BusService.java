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

	Optional<Bus> getBusById(long id);

	Bus saveBus(Bus Bus);

	BusBookingDetails bookBus(long id, BusTravellerBusSeats btbs, String username);

	BusBookingDetails paymentstatuschange(long bookingid);

	Bus updateBus(long id, Bus bus);

	String deleteBus(long id);

	BusBookingDetails resetStatus(long id);

	List<List<String>> getAllCityNames();

	List<Bus> getAllAvailableBuses(String from, String to, Date departure);

	Bus addSeats(long id);

	BusBookingDetails getBusBookingDetailsById(long id);

	List<BusBookingDetails> getBusBookingDetailsByUsername(String username);

}
