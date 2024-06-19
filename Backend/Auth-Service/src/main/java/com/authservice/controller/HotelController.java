package com.authservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.authservice.constant.HotelConstant;
import com.authservice.proxyentity.hotel.Hotel;
import com.authservice.proxyentity.hotel.HotelBookingDetails;
import com.authservice.proxyentity.hotel.HotelGuest;
import com.authservice.proxyentity.hotel.HotelProxyController;
import com.authservice.proxyentity.hotel.HotelRooms;

import jakarta.validation.Valid;

@RestController
@RequestMapping(HotelConstant.HOTEL)
@CrossOrigin(HotelConstant.CROSS_ORIGIN)
public class HotelController {

	@Autowired
	HotelProxyController hotelProxyController;

	@GetMapping(HotelConstant.GET_ALL_HOTEL)
	public ResponseEntity<List<Hotel>> getAllHotel() {
		return hotelProxyController.getAllHotel();
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_NAMES_BY_CITY)
	public ResponseEntity<List<String>> getHotelNamesByCity(@PathVariable String city) {
		return hotelProxyController.getHotelNamesByCity(city);
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_CITY_NAMES)
	public ResponseEntity<Set<String>> getAllHotelCityNames() {
		return hotelProxyController.getAllHotelCityNames();
	}

	@GetMapping(HotelConstant.GET_HOTEL_BY_HOTEL_ID)
	public ResponseEntity<Hotel> getHotelByHotelId(@PathVariable Long id) {
		return hotelProxyController.getHotelByHotelId(id);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BY_HOTEL_NAME)
	public ResponseEntity<Hotel> getHotelByHotelName(@PathVariable String hotelname) {
		return hotelProxyController.getHotelByHotelName(hotelname);
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_BY_CITY_NAME)
	public ResponseEntity<List<Hotel>> getAllHotelByCity(@PathVariable String cityname) {
		return hotelProxyController.getAllHotel();
	}

	@PostMapping(HotelConstant.ADD_HOTEL)
	public ResponseEntity<Hotel> addHotel(@RequestBody @Valid Hotel htl) throws Exception {
		return hotelProxyController.addHotel(htl);
	}

	@PutMapping(HotelConstant.UPDATE_HOTEL)
	public ResponseEntity<Hotel> updateHotel(@RequestBody @Valid Hotel ht) {
		return hotelProxyController.updateHotel(ht);
	}

	@DeleteMapping(HotelConstant.DELETE_HOTEL_BY_HOTEL_ID)
	public ResponseEntity<String> deleteHotelByHotelId(@PathVariable Long id) {
		return hotelProxyController.deleteHotelByHotelId(id);
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_BOOKING_DETAILS)
	public ResponseEntity<List<HotelBookingDetails>> listHotelBookingDetails() {
		return hotelProxyController.listHotelBookingDetails();
	}

	@GetMapping(HotelConstant.GET_ALL_AVAILABLE_ROOMS)
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city,
			@PathVariable String roomtype, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate) {
		return hotelProxyController.checkRoomAvailability(city, roomtype, fromDate, ToDate);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_ID)
	public ResponseEntity<HotelBookingDetails> showHotelBookingDetailsByHotelBookingId(@PathVariable long bookingid) {
		return hotelProxyController.showHotelBookingDetailsByHotelBookingId(bookingid);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_USERNAME_AND_HOTELNAME)
	public ResponseEntity<HotelBookingDetails> showHotelBookingDetailsByUserNameAndHotelName(@PathVariable String username,
			@PathVariable String hotelName) {
		return hotelProxyController.showHotelBookingDetailsByUserNameAndHotelName(username, hotelName);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<HotelBookingDetails>> showHotelBookingDetailsByUserName(@PathVariable String username) {
		return hotelProxyController.showHotelBookingDetailsByUserName(username);
	}

	@PutMapping(HotelConstant.PAYMENT_STATUS_CHANGE_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> paymentStatusChangeByHotelBookingId(@PathVariable long bookingid) {
		return hotelProxyController.paymentStatusChangeByHotelBookingId(bookingid);
	}

	@PostMapping(HotelConstant.BOOK_ROOM_BY_USERNAME)
	public ResponseEntity<HotelBookingDetails> bookroom(@PathVariable String username,
			@RequestBody HotelBookingDetails bd) {
		return hotelProxyController.bookroom(username, bd);
	}

	@DeleteMapping(HotelConstant.DELETE_HOTEL_BOOKING_DETAILS_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<String> removeHotelBookingDetailsByHotelBookingId(@PathVariable long bookingid) {
		return hotelProxyController.removeHotelBookingDetailsByHotelBookingId(bookingid);
	}

	@PostMapping(HotelConstant.ADD_GUEST_BY_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> addGuest(@PathVariable long bookingid,
			@RequestBody List<HotelGuest> guest) {
		return hotelProxyController.addGuest(bookingid, guest);
	}

	@PutMapping(HotelConstant.CANCEL_PAYMENT_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> cancelPaymentByHotelBookingId(@PathVariable long id) {
		return hotelProxyController.cancelPaymentByHotelBookingId(id);
	}

}
