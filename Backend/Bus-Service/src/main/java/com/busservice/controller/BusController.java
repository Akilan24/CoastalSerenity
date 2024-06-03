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
import com.busservice.entity.BusSeat;
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

	@GetMapping("/getbyid/{busId}")
	public ResponseEntity<Optional<Bus>> getBusBybusId(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.getBusById(busId), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Bus> createBus(@RequestBody Bus Bus) {
		return new ResponseEntity<>(BusService.saveBus(Bus), HttpStatus.CREATED);
	}

	@PutMapping("/update/{busId}")
	public ResponseEntity<Bus> updateBus(@PathVariable long busId, @RequestBody Bus Bus) {
		return new ResponseEntity<>(BusService.updateBus(busId, Bus), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<String> deleteBus(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.deleteBus(busId), HttpStatus.OK);

	}
	@PostMapping("/addseat/{busId}")
	public ResponseEntity<Bus> addseat(@PathVariable long busId,@RequestBody BusSeat busSeat) {
		return new ResponseEntity<>(BusService.addSeat(busId, busSeat), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateseat/{busId}")
	public ResponseEntity<Bus> updateseat(@PathVariable long busId,@RequestBody BusSeat busSeat) {
		return new ResponseEntity<>(BusService.updateSeat(busId, busSeat), HttpStatus.OK);
	}
	@PutMapping("/resetstatus/{busId}")
	public ResponseEntity<Bus> resetstatus(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.resetStatus(busId), HttpStatus.OK);
	}
}
