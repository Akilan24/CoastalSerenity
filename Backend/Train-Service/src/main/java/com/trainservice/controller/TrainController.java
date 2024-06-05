package com.trainservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.trainservice.service.TrainService;

@RestController
@RequestMapping("/Train")
public class TrainController {

	@Autowired
	private TrainService trainService;

	@GetMapping("/getall")
	public ResponseEntity<List<Train>> getAllTrains() {
		return new ResponseEntity<>(trainService.getAllTrains(), HttpStatus.OK);
	}

	@GetMapping("/getbyid/{TrainId}")
	public ResponseEntity<Optional<Train>> getTrainByTrainId(@PathVariable long TrainId) {
		return new ResponseEntity<>(trainService.getTrainById(TrainId), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Train> createTrain(@RequestBody Train Train) {
		return new ResponseEntity<>(trainService.saveTrain(Train), HttpStatus.CREATED);
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
	public ResponseEntity<Train> resetstatus(@PathVariable long TrainId) {
		return new ResponseEntity<>(trainService.resetStatus(TrainId), HttpStatus.OK);
	}
}
