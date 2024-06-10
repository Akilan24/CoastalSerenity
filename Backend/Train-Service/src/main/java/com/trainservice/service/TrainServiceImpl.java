package com.trainservice.service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainservice.entity.Train;
import com.trainservice.exception.TrainDetailsNotFoundException;
import com.trainservice.repository.TrainBookingStatusRepository;
import com.trainservice.repository.TrainRepository;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private TrainBookingStatusRepository trainBookingStatusRepository;

	@Override
	public List<Train> getAllTrains() {
		if (!trainRepository.findAll().isEmpty())
			return trainRepository.findAll();
		else
			throw new TrainDetailsNotFoundException("Train details are not found");
	}

	@Override
	public Optional<Train> getTrainById(long id) {
		Optional<Train> Train = trainRepository.findById(id);
		if (!Train.isEmpty())
			return Train;
		else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");
	}

	@Override
	public Train saveTrain(Train train) {
		long MIN_id = 100000;
		int count = trainRepository.findAll().size();
		train.setTrainId(count == 0 ? MIN_id : MIN_id + count);
		String[] parts = train.getDuration().split(":");
		long hours = Long.parseLong(parts[0]);
		long minutes = Long.parseLong(parts[1]);
		train.setArrivalTime(train.getDepartureTime().plus(Duration.ofHours(hours).plusMinutes(minutes)));
		return trainRepository.save(train);
	}

	@Override
	public String deleteTrain(long id) {
		Optional<Train> Train = trainRepository.findById(id);
		if (!Train.isEmpty()) {
			trainRepository.deleteById(id);
			return "Train details are deleted";
		} else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");

	}

	@Override
	public Train updateTrain(long id, Train Train) {
		Optional<Train> b = trainRepository.findById(id);
		if (!b.isEmpty()) {
			b.get().setArrivalTime(Train.getArrivalTime());
			b.get().setDepartureTime(Train.getDepartureTime());
			b.get().setDestination(Train.getDestination());
			b.get().setOrigin(Train.getOrigin());
			b.get().setDuration(Train.getDuration());
			return trainRepository.save(b.get());
		} else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");

	}

	@Override
	public Train resetStatus(long id) {
		Optional<Train> b = trainRepository.findById(id);
		if (b.isPresent()) {
			Map<String, Integer> map = b.get().getTrainBookingStatus().getBookingStatus();
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				entry.setValue(0);
			}
			b.get().getTrainBookingStatus().setBookingStatus(map);
			return trainRepository.save(b.get());
		} else
			throw new TrainDetailsNotFoundException("Train details of Train id: " + id + " are not found");

	}
}
