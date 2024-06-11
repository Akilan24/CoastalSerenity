package com.trainservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trainservice.entity.Train;
import com.trainservice.entity.TrainBookingDetails;
import com.trainservice.entity.TrainTravellerTrainSeats;

@Service
public interface TrainService {
	List<Train> getAllTrain();

	Optional<Train> getTrainById(long id);

	Train saveTrain(Train Train);

	TrainBookingDetails bookTrain(long id, TrainTravellerTrainSeats ttts, String username);

	TrainBookingDetails paymentstatuschange(long bookingid);

	Train updateTrain(long id, Train Train);

	String deleteTrain(long id);

	TrainBookingDetails resetStatus(long id);

	List<List<String>> getAllCityNames();

	List<Train> getAllAvailableTrains(String from, String to, Date departure);

	Train addSeats(long id);

	TrainBookingDetails getTrainBookingDetailsById(long id);

	List<TrainBookingDetails> getTrainBookingDetailsByUsername(String username);

}
