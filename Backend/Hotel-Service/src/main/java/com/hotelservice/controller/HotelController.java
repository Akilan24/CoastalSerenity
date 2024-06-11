package com.hotelservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.hotelservice.entity.HotelBookingDetails;
import com.hotelservice.entity.HotelGuest;
import com.hotelservice.entity.HotelRooms;
import com.hotelservice.service.HotelBookingDetailsService;
import com.hotelservice.service.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Hotel")
public class HotelController {
	@Autowired
	private HotelService hotelService;

	@Autowired
	HotelBookingDetailsService hotelBookingDetailsService;
	
	@GetMapping("/getallhotel")
	public ResponseEntity<List<Hotel>> getHotels() {
		return new ResponseEntity<>(hotelService.getHotels(), HttpStatus.OK);
	}

	@GetMapping("/getallhotelnamesbycity/{city}")
	public ResponseEntity<List<String>> getHotelNamesByCity(@PathVariable String city) {
		return new ResponseEntity<>(hotelService.getHotelNamesByCity(city), HttpStatus.OK);
	}

	@GetMapping("/getallhotelcitynames")
	public ResponseEntity<Set<String>> getHotelCityNames() {
		return new ResponseEntity<>(hotelService.getHotelCityNames(), HttpStatus.OK);
	}

	@GetMapping("/gethotelbyid/{id}")
	public ResponseEntity<Hotel> gethotelbyid(@PathVariable Long id) {
		return new ResponseEntity<>(hotelService.gethotelByHotelId(id), HttpStatus.OK);
	}

	@GetMapping("/gethotelbyhotelname/{hotelname}")
	public ResponseEntity<Hotel> gethotelbyhotelname(@PathVariable String hotelname) {
		return new ResponseEntity<>(hotelService.gethotelByHotelName(hotelname), HttpStatus.OK);
	}

	@GetMapping("/gethotelbycityname/{cityname}")
	public ResponseEntity<List<Hotel>> gethotelbycityname(@PathVariable String cityname) {
		return new ResponseEntity<>(hotelService.gethotelByCity(cityname), HttpStatus.OK);
	}

	@PostMapping("/addhotel")
	public ResponseEntity<Hotel> addhotel(@RequestBody @Valid Hotel htl) throws Exception {
		return new ResponseEntity<>(hotelService.addhotel(htl), HttpStatus.OK);
	}

	@PutMapping("/updatehotel")
	public ResponseEntity<Hotel> updatehotel(@RequestBody @Valid Hotel ht) {
		return new ResponseEntity<>(hotelService.updatehotel(ht), HttpStatus.OK);

	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deletehotel(@PathVariable Long id) {
		return new ResponseEntity<>(hotelService.deletehotel(id), HttpStatus.OK);
	}
	
	@GetMapping("/HotelBookingDetails/getall")
	public ResponseEntity<List<HotelBookingDetails>> listBookingDetails() {
		return new ResponseEntity<>(hotelBookingDetailsService.showAllBookingDetails(), HttpStatus.OK);
	}

	@GetMapping("/HotelBookingDetails/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city,
			@PathVariable String roomtype, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate) {
		return new ResponseEntity<>(hotelBookingDetailsService.AvailableRoom(roomtype, city, fromDate, ToDate), HttpStatus.OK);
	}

	@GetMapping("/HotelBookingDetails/getbyid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> getBookingDetails(@PathVariable long bookingid) {
		return new ResponseEntity<>(hotelBookingDetailsService.showBookingDetailsbyId(bookingid), HttpStatus.OK);
	}

	@GetMapping("/HotelBookingDetails/getbyusernameandhotelname/{username}/{hotelName}")
	public ResponseEntity<HotelBookingDetails> getBookingDetailsbyusernameandhotelname(@PathVariable String username,@PathVariable String hotelName) {
		return new ResponseEntity<>(hotelBookingDetailsService.showBookingDetailsbyUserNameAndHotelName(username,hotelName), HttpStatus.OK);
	}

	@GetMapping("/HotelBookingDetails/getbookingdetailsbyusername/{username}")
	public ResponseEntity<List<HotelBookingDetails>> getBookingDetailsbyusername(@PathVariable String username) {
		return new ResponseEntity<>(hotelBookingDetailsService.showBookingDetailsbyUserName(username), HttpStatus.OK);
	}
	
	@PutMapping("/HotelBookingDetails/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> paymentstatuschange(@PathVariable long bookingid) {
		return new ResponseEntity<>(hotelBookingDetailsService.paymentstatuschange(bookingid), HttpStatus.OK);
	}

	@PostMapping("/HotelBookingDetails/bookroom/{username}")
	public ResponseEntity<HotelBookingDetails> bookroom(@PathVariable String username,
			@RequestBody HotelBookingDetails bd) {
		return new ResponseEntity<>(hotelBookingDetailsService.BookRoom(username, bd), HttpStatus.OK);
	}

	@DeleteMapping("/HotelBookingDetails/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable long bookingid) {
		return new ResponseEntity<>(hotelBookingDetailsService.removeBookingDetails(bookingid), HttpStatus.OK);
	}

	@PostMapping("/HotelBookingDetails/addguests/{bookingid}")
	public ResponseEntity<HotelBookingDetails> addGuest(@PathVariable long bookingid,
			@RequestBody List<HotelGuest> guest) {
		return new ResponseEntity<>(hotelBookingDetailsService.addGuest(bookingid, guest), HttpStatus.OK);
	}
	
	@PutMapping("/HotelBookingDetails/resetstatus/{id}")
	public ResponseEntity<HotelBookingDetails> resetstatus(@PathVariable long id) {
		return new ResponseEntity<>(hotelBookingDetailsService.resetStatus(id), HttpStatus.OK);
	}

}
