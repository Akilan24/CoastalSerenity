package com.busservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.busservice.entity.Bus;
import com.busservice.entity.BusSeat;

@Service
public interface BusService {
	List<Bus> getAllBuss();

	Optional<Bus> getBusById(long id);

	Bus saveBus(Bus Bus);

	Bus updateBus(long id, Bus bus);

	String deleteBus(long id);

	Bus addSeat(long id, BusSeat busSeat);

	Bus updateSeat(long id, BusSeat busSeat);

	Bus resetStatus(long id);
}
