package com.busservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.busservice.entity.Bus;

@Service
public interface BusService {
	List<Bus> getAllBuss();

	Optional<Bus> getBusById(long id);

	Bus saveBus(Bus Bus);

	Bus updateBus(long id, Bus bus);

	String deleteBus(long id);

}
