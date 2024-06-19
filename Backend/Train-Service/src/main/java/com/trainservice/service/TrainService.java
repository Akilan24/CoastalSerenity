package com.trainservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trainservice.entity.Train;
import com.trainservice.entity.TrainBookingDetails;
import com.trainservice.externalclass.Traveller;

@Service
public interface TrainService {
	List<Train> getAllTrain();

	Optional<Train> getTrainByTrainId(long id);

	Train saveTrain(Train Train);

	TrainBookingDetails bookTrain(long id, List<Traveller> travellers, String boardingStations, String seatType,
			String username);

	TrainBookingDetails paymentStatusChangeByTrainBookingId(long bookingid);

	Train updateTrainByTrainId(long id, Train Train);

	String deleteTrainByTrainId(long id);

	TrainBookingDetails cancelPaymentByTrainBookingId(long id);

	List<List<String>> getAllCityNames();

	List<Train> getAllAvailableTrains(String from, String to, Date departure);

	Train addSeatsByTrainId(long id);

	TrainBookingDetails getTrainBookingDetailsByTrainBookingId(long id);

	List<TrainBookingDetails> getTrainBookingDetailsByUserName(String username);

}
