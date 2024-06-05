package com.cabservice.service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabservice.entity.Cab;
import com.cabservice.exception.CabDetailsNotFoundException;
import com.cabservice.repository.CabRepository;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	private CabRepository cabRepository;

	@Override
	public List<Cab> getAllCabs() {
		if (!cabRepository.findAll().isEmpty())
			return cabRepository.findAll();
		else
			throw new CabDetailsNotFoundException("Cab details are not found");
	}

	@Override
	public Optional<Cab> getCabById(long id) {
		Optional<Cab> Cab = cabRepository.findById(id);
		if (!Cab.isEmpty())
			return Cab;
		else
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + id + " are not found");
	}

	@Override
	public Cab saveCab(Cab Cab) {
		long MIN_id = 100000;
		int count = cabRepository.findAll().size();
		Cab.setCabId(count == 0 ? MIN_id : MIN_id + count);
		String[] parts = Cab.getDuration().split(":");
		long hours = Long.parseLong(parts[0]);
		long minutes = Long.parseLong(parts[1]);
		Cab.setArrivalTime(Cab.getDepartureTime().plus(Duration.ofHours(hours).plusMinutes(minutes)));
		return cabRepository.save(Cab);
	}

	@Override
	public String deleteCab(long id) {
		Optional<Cab> Cab = cabRepository.findById(id);
		if (!Cab.isEmpty()) {
			cabRepository.deleteById(id);
			return "Cab details are deleted";
		} else
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + id + " are not found");

	}

	@Override
	public Cab updateCab(long id, Cab cab) {
		Optional<Cab> f = cabRepository.findById(id);
		if (!f.isEmpty()) {
			f.get().setCabModel(null);
			f.get().setArrivalTime(cab.getArrivalTime());
			f.get().setDepartureTime(cab.getDepartureTime());
			f.get().setDestination(cab.getDestination());
			f.get().setOrigin(cab.getOrigin());
			f.get().setDuration(cab.getDuration());
			f.get().setCabImage(cab.getCabImage());
			f.get().setCabModel(cab.getCabModel());
			f.get().setCabPrice(cab.getCabPrice());
			f.get().setTotalSeat(cab.getTotalSeat());
			f.get().setDistance(cab.getDistance());
			f.get().setCabType(cab.getCabType());
			return cabRepository.save(f.get());
		} else
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + id + " are not found");

	}
	@Override
	public Cab resetStatus(long id) {
		Optional<Cab> b = cabRepository.findById(id);
		if (b.isPresent()) {
			b.get().setBookingStatus(false);
			return cabRepository.save(b.get());
		} else
			throw new CabDetailsNotFoundException("Cab details of Cab id: " + id + " are not found");

	}

}
