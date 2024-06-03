package com.busservice.controller;

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

import com.busservice.entity.Bus;
import com.busservice.service.BusService;

@RestController
@RequestMapping("/Bus")
public class BusController {

	@Autowired
	private BusService BusService;

	@GetMapping("/getall")
	public ResponseEntity<List<Bus>> getAllBuss() {
		return new ResponseEntity<>(BusService.getAllBuss(), HttpStatus.OK);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Optional<Bus>> getBusById(@PathVariable long id) {
		return new ResponseEntity<>(BusService.getBusById(id), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Bus> createBus(@RequestBody Bus Bus) {
		return new ResponseEntity<>(BusService.saveBus(Bus), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Bus> updateBus(@PathVariable long id, @RequestBody Bus Bus) {
		return new ResponseEntity<>(BusService.updateBus(id, Bus), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBus(@PathVariable long id) {
		return new ResponseEntity<>(BusService.deleteBus(id), HttpStatus.OK);

	}
}
