package com.cabservice.controller;

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

import com.cabservice.entity.Cab;
import com.cabservice.service.CabService;

@RestController
@RequestMapping("/Cab")
public class CabController {

    @Autowired
    private CabService cabService;

    @GetMapping("/getall")
    public ResponseEntity<List<Cab>> getAllCabs() {
        return new ResponseEntity<>(cabService.getAllCabs(), HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Optional<Cab>> getCabById(@PathVariable long id) {
        return new ResponseEntity<>(cabService.getCabById(id), HttpStatus.OK);
  }

    @PostMapping("/save")
    public ResponseEntity<Cab> createCab(@RequestBody Cab Cab) {
        return new ResponseEntity<>(cabService.saveCab(Cab), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cab> updateCab(@PathVariable long id, @RequestBody Cab Cab) {
        return new ResponseEntity<>(cabService.updateCab(id, Cab), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCab(@PathVariable long id) {
        return new ResponseEntity<>(cabService.deleteCab(id),HttpStatus.OK);
    }
    
    @PutMapping("/resetstatus/{id}")
	public ResponseEntity<Cab> resetstatus(@PathVariable long id) {
		return new ResponseEntity<>(cabService.resetStatus(id), HttpStatus.OK);
	}
}
