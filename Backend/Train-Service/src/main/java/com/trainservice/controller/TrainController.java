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

import com.trainservice.entity.Train;
import com.trainservice.entity.TrainBookingDetails;
import com.trainservice.entity.TrainTravellerTrainSeats;
import com.trainservice.service.TrainService;

@RestController
@RequestMapping("/Train")
public class TrainController {

	@Autowired
	private TrainService trainService;

	@GetMapping("/getall")
	public ResponseEntity<List<Train>> getAllTrain() {
		return new ResponseEntity<>(trainService.getAllTrain(), HttpStatus.OK);
	}

	@GetMapping("/getbyid/{TrainId}")
	public ResponseEntity<Optional<Train>> getTrainByTrainId(@PathVariable long TrainId) {
		return new ResponseEntity<>(trainService.getTrainById(TrainId), HttpStatus.OK);
	}

	@PutMapping("/addseats/{TrainId}")
	public ResponseEntity<Train> addseat(@PathVariable long TrainId) {
		return new ResponseEntity<>(trainService.addSeats(TrainId), HttpStatus.CREATED);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Train> createTrain(@RequestBody Train Train) {
		return new ResponseEntity<>(trainService.saveTrain(Train), HttpStatus.CREATED);
	}

	@PostMapping("/bookTrain/{id}/{username}")
	public ResponseEntity<TrainBookingDetails> bookTrain(@PathVariable long id,
			@RequestBody TrainTravellerTrainSeats ttts, @PathVariable String username) {
		return new ResponseEntity<>(trainService.bookTrain(id, ttts, username), HttpStatus.CREATED);
	}
	
	@GetMapping("/getTrainbookingdetailsbyid/{id}")
	public ResponseEntity<TrainBookingDetails> getTrainBookingDetailsById(@PathVariable long id) {
		return new ResponseEntity<>(trainService.getTrainBookingDetailsById(id), HttpStatus.OK);
	}
	
	@GetMapping("/getTrainbookingdetailsbyusername/{username}")
	public ResponseEntity<List<TrainBookingDetails>> getTrainBookingDetailsByUsername(@PathVariable String username) {
		return new ResponseEntity<>(trainService.getTrainBookingDetailsByUsername(username), HttpStatus.OK);
	}
	
	@PutMapping("/update/{TrainId}")
	public ResponseEntity<Train> updateTrain(@PathVariable long TrainId, @RequestBody Train Train) {
		return new ResponseEntity<>(trainService.updateTrain(TrainId, Train), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{TrainId}")
	public ResponseEntity<String> deleteTrain(@PathVariable long TrainId) {
		return new ResponseEntity<>(trainService.deleteTrain(TrainId), HttpStatus.OK);

	}

	@PutMapping("/resetstatus/{TrainId}")
	public ResponseEntity<TrainBookingDetails> resetstatus(@PathVariable long TrainId) {
		return new ResponseEntity<>(trainService.resetStatus(TrainId), HttpStatus.OK);
	}
	
	@GetMapping("/getallcitynames")
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return new ResponseEntity<>(trainService.getAllCityNames(), HttpStatus.OK);
	}
	
	@GetMapping("/paymentstatuschange/{bookingid}")
	public ResponseEntity<TrainBookingDetails> paymentstatuschange(@PathVariable long bookingid) {
		return new ResponseEntity<>(trainService.paymentstatuschange(bookingid), HttpStatus.OK);
	}

	@GetMapping("/getallavailableTraines/{from}/{to}/{departure}")
	public ResponseEntity<List<Train>> getAllAvailableTrains(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure) {
		return new ResponseEntity<>(trainService.getAllAvailableTrains(from, to, departure),
				HttpStatus.OK);
	}
}
