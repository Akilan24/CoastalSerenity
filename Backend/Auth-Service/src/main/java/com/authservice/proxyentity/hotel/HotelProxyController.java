package com.authservice.proxyentity.hotel;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.proxyentity.hotel.Hotel;

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

	@GetMapping("/HotelBookingDetails/getall")
	public ResponseEntity<List<HotelBookingDetails>> listBookingDetails();

	@GetMapping("/HotelBookingDetails/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city,
			@PathVariable String roomtype, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate);

	@GetMapping("/HotelBookingDetails/getbyid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> getBookingDetails(@PathVariable long bookingid);

	@GetMapping("/HotelBookingDetails/getbyusername/{username}/{hotelName}")
	public ResponseEntity<HotelBookingDetails> getBookingDetailsbyusernameandhotelname(@PathVariable String username,
			@PathVariable String hotelName);

	@GetMapping("/HotelBookingDetails/getbyusername/{username}")
	public ResponseEntity<List<HotelBookingDetails>> getBookingDetailsbyusername(@PathVariable String username);

	@PutMapping("/HotelBookingDetails/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> paymentstatuschange(@PathVariable long bookingid);

	@PostMapping("/HotelBookingDetails/bookroom/{username}")
	public ResponseEntity<HotelBookingDetails> bookroom(@PathVariable String username,
			@RequestBody HotelBookingDetails bd);

	@DeleteMapping("/HotelBookingDetails/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable long bookingid);

	@PostMapping("/HotelBookingDetails/addguests/{bookingid}")
	public ResponseEntity<HotelBookingDetails> addGuest(@PathVariable long bookingid,
			@RequestBody List<HotelGuest> guest);

}
