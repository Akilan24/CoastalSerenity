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

import com.authservice.proxyentity.bus.Bus;
import com.authservice.proxyentity.bus.BusProxyController;
import com.authservice.proxyentity.bus.BusSeat;

@RestController
@RequestMapping("/CS/Bus")
@CrossOrigin("http://localhost:5173")
public class BusController {

	@Autowired
	private BusProxyController busProxy;

	@GetMapping("/getall")
	public ResponseEntity<List<Bus>> getAllBus() {
		return busProxy.getAllBus();
	}

	@GetMapping("/getbyid/{busId}")
	public ResponseEntity<Optional<Bus>> getBusBybusId(@PathVariable long busId) {
		return busProxy.getBusBybusId(busId);
	}

	@PostMapping("/save")
	public ResponseEntity<Bus> createBus(@RequestBody Bus Bus) {
		return busProxy.createBus(Bus);
	}

	@PutMapping("/update/{busId}")
	public ResponseEntity<Bus> updateBus(@PathVariable long busId, @RequestBody Bus Bus) {
		return busProxy.updateBus(busId, Bus);
	}

	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<String> deleteBus(@PathVariable long busId) {
		return busProxy.deleteBus(busId);
	}

	@PostMapping("/addseat/{busId}")
	public ResponseEntity<Bus> addseat(@PathVariable long busId, @RequestBody BusSeat busSeat) {
		return busProxy.addseat(busId, busSeat);
	}

	@PutMapping("/updateseat/{busId}")
	public ResponseEntity<Bus> updateseat(@PathVariable long busId, @RequestBody BusSeat busSeat) {
		return busProxy.updateBus(busId, null);
	}

	@PutMapping("/resetstatus/{busId}")
	public ResponseEntity<Bus> resetstatus(@PathVariable long busId) {
		return busProxy.resetstatus(busId);
	}
}
