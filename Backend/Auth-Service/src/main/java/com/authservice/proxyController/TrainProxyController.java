package com.authservice.proxyController;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.ProxyEntity.Train;

@FeignClient(name = "TRAIN-SERVICE", url = "http://localhost:8088/Train")
public interface TrainProxyController {

	@GetMapping("/getall")
	public ResponseEntity<List<Train>> getAllTrains();

	@GetMapping("/getbyid/{TrainId}")
	public ResponseEntity<Optional<Train>> getTrainByTrainId(@PathVariable long TrainId);

	@PostMapping("/save")
	public ResponseEntity<Train> createTrain(@RequestBody Train Train);

	@PutMapping("/update/{TrainId}")
	public ResponseEntity<Train> updateTrain(@PathVariable long TrainId, @RequestBody Train Train);

	@DeleteMapping("/delete/{TrainId}")
	public ResponseEntity<String> deleteTrain(@PathVariable long TrainId);

	@PutMapping("/resetstatus/{TrainId}")
	public ResponseEntity<Train> resetstatus(@PathVariable long TrainId);
}
