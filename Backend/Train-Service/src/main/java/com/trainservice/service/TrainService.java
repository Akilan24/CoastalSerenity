package com.trainservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trainservice.entity.Train;

@Service
public interface TrainService {
	List<Train> getAllTrains();

	Optional<Train> getTrainById(long id);

	Train saveTrain(Train Train);

	Train updateTrain(long id, Train Train);

	String deleteTrain(long id);

	Train resetStatus(long id);
}
