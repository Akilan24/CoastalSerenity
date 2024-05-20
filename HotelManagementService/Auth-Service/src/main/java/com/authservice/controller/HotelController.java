package com.authservice.controller;

import java.util.List;
import java.util.Set;

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

import com.authservice.ProxyEntity.Hotel;
import com.authservice.proxyController.HotelProxyController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/HMA/Hotel")
@CrossOrigin("http://localhost:5173")
public class HotelController {
	@Autowired
	private HotelProxyController hotelProxy;

	@GetMapping("/getallhotel")
	public ResponseEntity<List<Hotel>> getHotels() {
		return hotelProxy.getHotels();
	}

	@GetMapping("/getallhotelcitynames")
	public ResponseEntity<Set<String>> getHotelCityNames() {
		return hotelProxy.getHotelCityNames();
	}

	@GetMapping("/getallhotelnamesbycity/{city}")
	public ResponseEntity<List<String>> getHotelNamesByCity(@PathVariable String city) {
		return hotelProxy.getHotelNamesByCity(city);
	}

	@GetMapping("/gethotelbyid/{id}")
	public ResponseEntity<Hotel> gethotelbyid(@PathVariable Long id) {
		return hotelProxy.gethotelbyid(id);
	}

	@GetMapping("/gethotelbyhotelname/{hotelname}")
	public ResponseEntity<Hotel> gethotelbyhotelname(@PathVariable String hotelname) {
		return hotelProxy.gethotelbyhotelname(hotelname);
	}

	@GetMapping("/gethotelbycityname/{cityname}")
	public ResponseEntity<List<Hotel>> gethotelbycityname(@PathVariable String cityname) {
		return hotelProxy.gethotelbycityname(cityname);
	}

	@PostMapping("/addhotel")
	public ResponseEntity<Hotel> addhotel(@RequestBody @Valid Hotel htl) throws Exception {
		return hotelProxy.addhotel(htl);
	}

	@PutMapping("/updatehotel")
	public ResponseEntity<Hotel> updatehotel(@RequestBody @Valid Hotel ht) {
		return hotelProxy.updatehotel(ht);

	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deletehotel(@PathVariable Long id) {
		return hotelProxy.deletehotel(id);
	}
}
