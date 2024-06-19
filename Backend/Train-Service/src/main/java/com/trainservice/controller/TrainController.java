package com.trainservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainservice.constant.TrainConstant;
import com.trainservice.entity.Train;
import com.trainservice.entity.TrainBookingDetails;
import com.trainservice.externalclass.Traveller;
import com.trainservice.service.TrainService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(TrainConstant.TRAIN)
public class TrainController {

	@Autowired
	private TrainService trainService;

	@GetMapping(TrainConstant.GET_ALL_TRAIN)
	public ResponseEntity<List<Train>> getAllTrain() {
		log.info("getAllTrain controller called");
		return new ResponseEntity<>(trainService.getAllTrain(), HttpStatus.OK);
	}

	@GetMapping(TrainConstant.GET_TRAIN_BY_TRAIN_ID)
	public ResponseEntity<Optional<Train>> getTrainByTrainId(@PathVariable long TrainId) {
		log.info("getTrainByTrainId controller called");
		return new ResponseEntity<>(trainService.getTrainByTrainId(TrainId), HttpStatus.OK);
	}

	@PutMapping(TrainConstant.ADD_SEATS_BY_TRAIN_ID)
	public ResponseEntity<Train> addSeatsByTrainId(@PathVariable long TrainId) {
		log.info("addSeatsByTrainId controller called");
		return new ResponseEntity<>(trainService.addSeatsByTrainId(TrainId), HttpStatus.CREATED);
	}

	@PostMapping(TrainConstant.SAVE_TRAIN)
	public ResponseEntity<Train> saveTrain(@RequestBody Train Train) {
		log.info("saveTrain controller called");
		return new ResponseEntity<>(trainService.saveTrain(Train), HttpStatus.CREATED);
	}

	@PostMapping(TrainConstant.BOOK_TRAIN_BY_TRAIN_ID)
	public ResponseEntity<TrainBookingDetails> bookTrain(@PathVariable long id, @RequestBody List<Traveller> travellers,
			@PathVariable String seatType, @PathVariable String boardingStation, @PathVariable String username) {
		log.info("bookTrain controller called");
		return new ResponseEntity<>(trainService.bookTrain(id, travellers, seatType, boardingStation, username),
				HttpStatus.CREATED);
	}

	@GetMapping(TrainConstant.GET_TRAIN_BOOKING_DETAILS_BY_ID)
	public ResponseEntity<TrainBookingDetails> getTrainBookingDetailsByTrainBookingId(@PathVariable long id) {
		log.info("getTrainBookingDetailsById controller called");
		return new ResponseEntity<>(trainService.getTrainBookingDetailsByTrainBookingId(id), HttpStatus.OK);
	}

	@GetMapping(TrainConstant.GET_TRAIN_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<TrainBookingDetails>> getTrainBookingDetailsByUserName(@PathVariable String username) {
		log.info("getTrainBookingDetailsByUserName controller called");
		return new ResponseEntity<>(trainService.getTrainBookingDetailsByUserName(username), HttpStatus.OK);
	}

	@PutMapping(TrainConstant.UPDATE_TRAIN_BY_TRAIN_ID)
	public ResponseEntity<Train> updateTrainByTrainId(@PathVariable long TrainId, @RequestBody Train Train) {
		log.info("updateTrainByTrainId controller called");
		return new ResponseEntity<>(trainService.updateTrainByTrainId(TrainId, Train), HttpStatus.OK);
	}

	@DeleteMapping(TrainConstant.DELETE_TRAIN_BY_TRAIN_ID)
	public ResponseEntity<String> deleteTrainByTrainId(@PathVariable long TrainId) {
		log.info("deleteTrainByTrainId controller called");
		return new ResponseEntity<>(trainService.deleteTrainByTrainId(TrainId), HttpStatus.OK);

	}

	@PutMapping(TrainConstant.CANCEL_PAYMENT_BY_TRAIN_BOOKING_ID)
	public ResponseEntity<TrainBookingDetails> cancelPaymentByTrainBookingId(@PathVariable long TrainId) {
		log.info("cancelPaymentByTrainBookingId controller called");
		return new ResponseEntity<>(trainService.cancelPaymentByTrainBookingId(TrainId), HttpStatus.OK);
	}

	@GetMapping(TrainConstant.GET_ALL_CITY_NAMES)
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		log.info("getAllCityNames controller called");
		return new ResponseEntity<>(trainService.getAllCityNames(), HttpStatus.OK);
	}

	@GetMapping(TrainConstant.PAYMENT_STATUS_CHANGE_BY_TRAIN_BOOKING_ID)
	public ResponseEntity<TrainBookingDetails> paymentStatusChangeByTrainBookingId(@PathVariable long bookingid) {
		log.info("paymentStatusChangeByTrainBookingId controller called");
		return new ResponseEntity<>(trainService.paymentStatusChangeByTrainBookingId(bookingid), HttpStatus.OK);
	}

	@GetMapping(TrainConstant.GET_ALL_AVAILABLE_TRAINS)
	public ResponseEntity<List<Train>> getAllAvailableTrains(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure) {
		log.info("getAllAvailableTrains controller called");
		return new ResponseEntity<>(trainService.getAllAvailableTrains(from, to, departure), HttpStatus.OK);
	}
}
