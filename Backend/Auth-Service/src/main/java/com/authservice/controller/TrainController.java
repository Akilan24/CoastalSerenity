package com.authservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.entity.Traveller;
import com.authservice.proxyentity.train.Train;
import com.authservice.proxyentity.train.TrainBookingDetails;
import com.authservice.proxyentity.train.TrainProxyController;

@RestController
@RequestMapping("/CS/Train")
@CrossOrigin("http://localhost:5173")
public class TrainController {

	@Autowired
	private TrainProxyController trainProxy;

	@GetMapping("/getall")
	public ResponseEntity<List<Train>> getAllTrain() {
		return trainProxy.getAllTrain();
	}

	@GetMapping("/getbyid/{TrainId}")
	public ResponseEntity<Optional<Train>> getTrainByTrainId(@PathVariable long TrainId) {
		return trainProxy.getTrainByTrainId(TrainId);
	}

	@PutMapping("/addseats/{TrainId}")
	public ResponseEntity<Train> addseat(@PathVariable long TrainId) {
		return trainProxy.addseat(TrainId);
	}

	@PostMapping("/save")
	public ResponseEntity<Train> createTrain(@RequestBody Train Train) {
		return trainProxy.createTrain(Train);
	}

	@PostMapping("/bookTrain/{id}/{seatType}/{boardingStation}/{username}")
	public ResponseEntity<TrainBookingDetails> bookTrain(@PathVariable long id,
			@RequestBody List<Traveller> travellers,@PathVariable String seatType,@PathVariable String boardingStation, @PathVariable String username)  {
		return trainProxy.bookTrain(id, travellers,seatType,boardingStation, username);
	}

	@GetMapping("/getTrainbookingdetailsbyid/{id}")
	public ResponseEntity<TrainBookingDetails> getTrainBookingDetailsById(@PathVariable long id) {
		return trainProxy.getTrainBookingDetailsById(id);
	}

	@GetMapping("/getTrainbookingdetailsbyusername/{username}")
	public ResponseEntity<List<TrainBookingDetails>> getTrainBookingDetailsByUsername(@PathVariable String username) {
		return trainProxy.getTrainBookingDetailsByUsername(username);
	}

	@PutMapping("/update/{TrainId}")
	public ResponseEntity<Train> updateTrain(@PathVariable long TrainId, @RequestBody Train Train) {
		return trainProxy.updateTrain(TrainId, Train);
	}

	@DeleteMapping("/delete/{TrainId}")
	public ResponseEntity<String> deleteTrain(@PathVariable long TrainId) {
		return trainProxy.deleteTrain(TrainId);

	}

	@PutMapping("/resetstatus/{TrainId}")
	public ResponseEntity<TrainBookingDetails> resetstatus(@PathVariable long TrainId) {
		return trainProxy.resetstatus(TrainId);
	}

	@GetMapping("/getallcitynames")
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return trainProxy.getAllCityNames();
	}

	@GetMapping("/paymentstatuschange/{bookingid}")
	public ResponseEntity<TrainBookingDetails> paymentstatuschange(@PathVariable long bookingid) {
		return trainProxy.paymentstatuschange(bookingid);
	}

	@GetMapping("/getallavailableTrains/{from}/{to}/{departure}")
	public ResponseEntity<List<Train>> getAllAvailableTrains(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure) {
		return trainProxy.getAllAvailableTrains(from, to, departure);
	}
}
