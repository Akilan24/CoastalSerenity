package com.authservice.proxyentity.train;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.entity.Traveller;

@FeignClient(name = "TRAIN-SERVICE", url = "http://localhost:8088/Train")
public interface TrainProxyController {

	@GetMapping("/getall")
	public ResponseEntity<List<Train>> getAllTrain() ;

	@GetMapping("/getbyid/{TrainId}")
	public ResponseEntity<Optional<Train>> getTrainByTrainId(@PathVariable long TrainId);

	@PutMapping("/addseats/{TrainId}")
	public ResponseEntity<Train> addseat(@PathVariable long TrainId);
	
	@PostMapping("/save")
	public ResponseEntity<Train> createTrain(@RequestBody Train Train) ;

	@PostMapping("/bookTrain/{id}/{username}/{boardingStation}/{seatType}")
	public ResponseEntity<TrainBookingDetails> bookTrain(@PathVariable long id,
			@RequestBody List<Traveller> travellers,@PathVariable String boardingStation,@PathVariable String seatType, @PathVariable String username) ;
	
	@GetMapping("/getTrainbookingdetailsbyid/{id}")
	public ResponseEntity<TrainBookingDetails> getTrainBookingDetailsById(@PathVariable long id);
	
	@GetMapping("/getTrainbookingdetailsbyusername/{username}")
	public ResponseEntity<List<TrainBookingDetails>> getTrainBookingDetailsByUsername(@PathVariable String username);
	@PutMapping("/update/{TrainId}")
	public ResponseEntity<Train> updateTrain(@PathVariable long TrainId, @RequestBody Train Train) ;

	@DeleteMapping("/delete/{TrainId}")
	public ResponseEntity<String> deleteTrain(@PathVariable long TrainId) ;
	@PutMapping("/resetstatus/{TrainId}")
	public ResponseEntity<TrainBookingDetails> resetstatus(@PathVariable long TrainId);
	@GetMapping("/getallcitynames")
	public ResponseEntity<List<List<String>>> getAllCityNames() ;
	
	@GetMapping("/paymentstatuschange/{bookingid}")
	public ResponseEntity<TrainBookingDetails> paymentstatuschange(@PathVariable long bookingid) ;
	@GetMapping("/getallavailableTrains/{from}/{to}/{departure}")
	public ResponseEntity<List<Train>> getAllAvailableTrains(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure) ;
}
