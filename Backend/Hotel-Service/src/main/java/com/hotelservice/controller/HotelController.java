package com.hotelservice.controller;

import java.util.List;
import java.util.Set;

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

import com.hotelservice.entity.Hotel;
import com.hotelservice.service.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Hotel")
public class HotelController {
	@Autowired
	private HotelService hs;

	@GetMapping("/getallhotel")
	public ResponseEntity<List<Hotel>> getHotels() {
		return new ResponseEntity<>(hs.getHotels(), HttpStatus.OK);
	}

	@GetMapping("/getallhotelnamesbycity/{city}")
	public ResponseEntity<List<String>> getHotelNamesByCity(@PathVariable String city) {
		return new ResponseEntity<>(hs.getHotelNamesByCity(city), HttpStatus.OK);
	}

	@GetMapping("/getallhotelcitynames")
	public ResponseEntity<Set<String>> getHotelCityNames() {
		return new ResponseEntity<>(hs.getHotelCityNames(), HttpStatus.OK);
	}

	@GetMapping("/gethotelbyid/{id}")
	public ResponseEntity<Hotel> gethotelbyid(@PathVariable Long id) {
		return new ResponseEntity<>(hs.gethotelByHotelId(id), HttpStatus.OK);
	}

	@GetMapping("/gethotelbyhotelname/{hotelname}")
	public ResponseEntity<Hotel> gethotelbyhotelname(@PathVariable String hotelname) {
		return new ResponseEntity<>(hs.gethotelByHotelName(hotelname), HttpStatus.OK);
	}

	@GetMapping("/gethotelbycityname/{cityname}")
	public ResponseEntity<List<Hotel>> gethotelbycityname(@PathVariable String cityname) {
		return new ResponseEntity<>(hs.gethotelByCity(cityname), HttpStatus.OK);
	}

	@PostMapping("/addhotel")
	public ResponseEntity<Hotel> addhotel(@RequestBody @Valid Hotel htl) throws Exception {
		return new ResponseEntity<>(hs.addhotel(htl), HttpStatus.OK);
	}

	@PutMapping("/updatehotel")
	public ResponseEntity<Hotel> updatehotel(@RequestBody @Valid Hotel ht) {
		return new ResponseEntity<>(hs.updatehotel(ht), HttpStatus.OK);

	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deletehotel(@PathVariable Long id) {
		return new ResponseEntity<>(hs.deletehotel(id), HttpStatus.OK);
	}
}
