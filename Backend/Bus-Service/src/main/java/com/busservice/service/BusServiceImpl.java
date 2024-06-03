package com.busservice.service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busservice.entity.Bus;
import com.busservice.exception.BusDetailsNotFoundException;
import com.busservice.repository.BusRepository;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusRepository BusRepository;

	@Override
	public List<Bus> getAllBuss() {
		if (!BusRepository.findAll().isEmpty())
			return BusRepository.findAll();
		else
			throw new BusDetailsNotFoundException("Bus details are not found");
	}

	@Override
	public Optional<Bus> getBusById(long id) {
		Optional<Bus> Bus = BusRepository.findById(id);
		if (!Bus.isEmpty())
			return Bus;
		else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");
	}

	@Override
	public Bus saveBus(Bus bus) {
		long MIN_id = 100000;
		int count = BusRepository.findAll().size();
		bus.setBusId(count == 0 ? MIN_id : MIN_id + count);
		String[] parts = bus.getDuration().split(":");
		long hours = Long.parseLong(parts[0]);
		long minutes = Long.parseLong(parts[1]);
		bus.setArrivalTime(bus.getDepartureTime().plus(Duration.ofHours(hours).plusMinutes(minutes)));
		return BusRepository.save(bus);
	}

	@Override
	public String deleteBus(long id) {
		Optional<Bus> Bus = BusRepository.findById(id);
		if (!Bus.isEmpty()) {
			BusRepository.deleteById(id);
			return "Bus details are deleted";
		} else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");

	}

	@Override
	public Bus updateBus(long id, Bus bus) {
		Optional<Bus> b = BusRepository.findById(id);
		if (!b.isEmpty()) {
			b.get().setArrivalTime(bus.getArrivalTime());
			b.get().setDepartureTime(bus.getDepartureTime());
			b.get().setDestination(bus.getDestination());
			b.get().setOrigin(bus.getOrigin());
			b.get().setDuration(null);
			return BusRepository.save(b.get());
		} else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");

	}
}
