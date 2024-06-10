package com.authservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.authservice.proxyentity.train.Train;
import com.authservice.proxyentity.train.TrainProxyController;

@RestController
@RequestMapping("/CS/Train")
@CrossOrigin("http://localhost:5173")
public class TrainController {

	@Autowired
	private TrainProxyController trainProxy;

	@GetMapping("/getall")
	public ResponseEntity<List<Train>> getAllTrains() {
		return trainProxy.getAllTrains();
	}

	@GetMapping("/getbyid/{TrainId}")
	public ResponseEntity<Optional<Train>> getTrainByTrainId(@PathVariable long TrainId) {
		return trainProxy.getTrainByTrainId(TrainId);
	}

	@PostMapping("/save")
	public ResponseEntity<Train> createTrain(@RequestBody Train Train) {
		return trainProxy.createTrain(Train);
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
	public ResponseEntity<Train> resetstatus(@PathVariable long TrainId) {
		return trainProxy.resetstatus(TrainId);
	}

}
