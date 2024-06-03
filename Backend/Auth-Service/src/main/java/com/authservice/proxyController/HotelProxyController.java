package com.authservice.proxyController;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.ProxyEntity.Hotel;

import jakarta.validation.Valid;

@FeignClient(name = "HOTEL-SERVICE", url = "http://localhost:8082/Hotel")
public interface HotelProxyController {

	@GetMapping("/getallhotel")
	public ResponseEntity<List<Hotel>> getHotels();

	@GetMapping("/getallhotelcitynames")
	public ResponseEntity<Set<String>> getHotelCityNames();

	@GetMapping("/getallhotelnamesbycity/{city}")
	public ResponseEntity<List<String>> getHotelNamesByCity(@PathVariable String city);

	@GetMapping("/gethotelbyid/{id}")
	public ResponseEntity<Hotel> gethotelbyid(@PathVariable Long id);

	@GetMapping("/gethotelbyhotelname/{hotelname}")
	public ResponseEntity<Hotel> gethotelbyhotelname(@PathVariable String hotelname);

	@GetMapping("/gethotelbycityname/{cityname}")
	public ResponseEntity<List<Hotel>> gethotelbycityname(@PathVariable String cityname);

	@PostMapping("/addhotel")
	public ResponseEntity<Hotel> addhotel(@RequestBody @Valid Hotel htl) throws Exception;

	@PutMapping("/updatehotel")
	public ResponseEntity<Hotel> updatehotel(@RequestBody @Valid Hotel ht);

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deletehotel(@PathVariable Long id);
}
