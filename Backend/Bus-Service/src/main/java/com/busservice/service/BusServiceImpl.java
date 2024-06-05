package com.busservice.service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busservice.entity.Bus;
import com.busservice.entity.BusSeat;
import com.busservice.exception.BusDetailsNotFoundException;
import com.busservice.repository.BusRepository;
import com.busservice.repository.BusSeatRepository;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private BusSeatRepository busSeatRepository;

	@Override
	public List<Bus> getAllBus() {
		if (!busRepository.findAll().isEmpty())
			return busRepository.findAll();
		else
			throw new BusDetailsNotFoundException("Bus details are not found");
	}

	@Override
	public Optional<Bus> getBusById(long id) {
		Optional<Bus> Bus = busRepository.findById(id);
		if (!Bus.isEmpty())
			return Bus;
		else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");
	}

	@Override
	public Bus saveBus(Bus bus) {
		long MIN_id = 100000;
		int count = busRepository.findAll().size();
		bus.setBusId(count == 0 ? MIN_id : MIN_id + count);
		String[] parts = bus.getDuration().split(":");
		long hours = Long.parseLong(parts[0]);
		long minutes = Long.parseLong(parts[1]);
		bus.setArrivalTime(bus.getDepartureTime().plus(Duration.ofHours(hours).plusMinutes(minutes)));
		return busRepository.save(bus);
	}

	@Override
	public String deleteBus(long id) {
		Optional<Bus> Bus = busRepository.findById(id);
		if (!Bus.isEmpty()) {
			busRepository.deleteById(id);
			return "Bus details are deleted";
		} else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");

	}

	@Override
	public Bus updateBus(long id, Bus bus) {
		Optional<Bus> b = busRepository.findById(id);
		if (!b.isEmpty()) {
			b.get().setArrivalTime(bus.getArrivalTime());
			b.get().setDepartureTime(bus.getDepartureTime());
			b.get().setDestination(bus.getDestination());
			b.get().setOrigin(bus.getOrigin());
			b.get().setDuration(bus.getDuration());
			return busRepository.save(b.get());
		} else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");

	}

	@Override
	public Bus addSeat(long id, BusSeat busSeat) {
		Optional<Bus> b = busRepository.findById(id);
		if (!b.isEmpty()) {
			b.get().getBusSeat().add(busSeat);
			return busRepository.save(b.get());
		} else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");

	}

	@Override
	public Bus updateSeat(long id, BusSeat busSeat) {
		Optional<Bus> b = busRepository.findById(id);
		if (b.isPresent()) {
			List<BusSeat> busSeats = b.get().getBusSeat();
			for (BusSeat seat : busSeats) {
				if (seat.getSeatNo().equalsIgnoreCase(busSeat.getSeatNo())) {
					BusSeat bs = busSeatRepository.findById(seat.getSeatId()).get();
					bs.setSeatPrice(busSeat.getSeatPrice());
					bs.setSeatType(busSeat.getSeatType());
					bs.setBookingStatus(busSeat.isBookingStatus());
					busSeatRepository.save(bs);
				}
			}
			b.get().setBusSeat(busSeats);
			return busRepository.findById(id).get();
		} else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");

	}

	@Override
	public Bus resetStatus(long id) {
		Optional<Bus> b = busRepository.findById(id);
		if (b.isPresent()) {
			List<BusSeat> busSeats = b.get().getBusSeat();
			for (BusSeat seat : busSeats) {
				seat.setBookingStatus(true);
				busSeatRepository.save(seat);
			}
			b.get().setBusSeat(busSeats);
			return busRepository.findById(id).get();
		} else
			throw new BusDetailsNotFoundException("Bus details of Bus id: " + id + " are not found");

	}
}
