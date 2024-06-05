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

import com.authservice.ProxyEntity.Cab;
import com.authservice.proxyController.CabProxyController;

@RestController
@RequestMapping("/HMA/Cab")
@CrossOrigin("http://localhost:5173")
public class CabController {

	@Autowired
	private CabProxyController cabProxy;

	@GetMapping("/getall")
	public ResponseEntity<List<Cab>> getAllCabs() {
		return cabProxy.getAllCabs();
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Optional<Cab>> getCabById(@PathVariable long id) {
		return cabProxy.getCabById(id);
	}

	@PostMapping("/save")
	public ResponseEntity<Cab> createCab(@RequestBody Cab Cab) {
		return cabProxy.createCab(Cab);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Cab> updateCab(@PathVariable long id, @RequestBody Cab Cab) {
		return cabProxy.updateCab(id, Cab);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCab(@PathVariable long id) {
		return cabProxy.deleteCab(id);
	}

	@PutMapping("/resetstatus/{id}")
	public ResponseEntity<Cab> resetstatus(@PathVariable long id) {
		return cabProxy.resetstatus(id);
	}
}
