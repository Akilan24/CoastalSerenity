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
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(HotelConstant.HOTEL)
@CrossOrigin(HotelConstant.CROSS_ORIGIN)
public class HotelController {

	@Autowired
	HotelProxyController hotelProxyController;

	@GetMapping(HotelConstant.GET_ALL_HOTEL)
	public ResponseEntity<List<Hotel>> getAllHotel() {
		log.info("getAllHotel controller called");
		return hotelProxyController.getAllHotel();
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_NAMES_BY_CITY)
	public ResponseEntity<List<String>> getHotelNamesByCity(@PathVariable String city) {
		log.info("getHotelNamesByCity controller called");
		return hotelProxyController.getHotelNamesByCity(city);
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_CITY_NAMES)
	public ResponseEntity<Set<String>> getAllHotelCityNames() {
		log.info("getAllHotelCityNames controller called");
		return hotelProxyController.getAllHotelCityNames();
	}

	@GetMapping(HotelConstant.GET_HOTEL_BY_HOTEL_ID)
	public ResponseEntity<Hotel> getHotelByHotelId(@PathVariable Long id) {
		log.info("getHotelByHotelId controller called");
		return hotelProxyController.getHotelByHotelId(id);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BY_HOTEL_NAME)
	public ResponseEntity<Hotel> getHotelByHotelName(@PathVariable String hotelname) {
		log.info("getHotelByHotelName controller called");
		return hotelProxyController.getHotelByHotelName(hotelname);
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_BY_CITY_NAME)
	public ResponseEntity<List<Hotel>> getAllHotelByCity(@PathVariable String cityname) {
		log.info("getAllHotelByCity controller called");
		return hotelProxyController.getAllHotel();
	}

	@PostMapping(HotelConstant.ADD_HOTEL)
	public ResponseEntity<Hotel> addHotel(@RequestBody @Valid Hotel htl) throws Exception {
		log.info("addHotel controller called");
		return hotelProxyController.addHotel(htl);
	}

	@PutMapping(HotelConstant.UPDATE_HOTEL)
	public ResponseEntity<Hotel> updateHotel(@RequestBody @Valid Hotel ht) {
		log.info("updateHotel controller called");
		return hotelProxyController.updateHotel(ht);
	}

	@DeleteMapping(HotelConstant.DELETE_HOTEL_BY_HOTEL_ID)
	public ResponseEntity<String> deleteHotelByHotelId(@PathVariable Long id) {
		log.info("deleteHotelByHotelId controller called");
		return hotelProxyController.deleteHotelByHotelId(id);
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_BOOKING_DETAILS)
	public ResponseEntity<List<HotelBookingDetails>> listHotelBookingDetails() {
		log.info("listHotelBookingDetails controller called");
		return hotelProxyController.listHotelBookingDetails();
	}

	@GetMapping(HotelConstant.GET_ALL_AVAILABLE_ROOMS)
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city,
			@PathVariable String roomtype, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate) {
		log.info("checkRoomAvailability controller called");
		return hotelProxyController.checkRoomAvailability(city, roomtype, fromDate, ToDate);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_ID)
	public ResponseEntity<HotelBookingDetails> showHotelBookingDetailsByHotelBookingId(@PathVariable long bookingid) {
		log.info("showHotelBookingDetailsByHotelBookingId controller called");
		return hotelProxyController.showHotelBookingDetailsByHotelBookingId(bookingid);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_USERNAME_AND_HOTELNAME)
	public ResponseEntity<HotelBookingDetails> showHotelBookingDetailsByUserNameAndHotelName(
			@PathVariable String username, @PathVariable String hotelName) {
		log.info("showHotelBookingDetailsByUserNameAndHotelName controller called");
		return hotelProxyController.showHotelBookingDetailsByUserNameAndHotelName(username, hotelName);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<HotelBookingDetails>> showHotelBookingDetailsByUserName(@PathVariable String username) {
		log.info("showHotelBookingDetailsByUserName controller called");
		return hotelProxyController.showHotelBookingDetailsByUserName(username);
	}

	@PutMapping(HotelConstant.PAYMENT_STATUS_CHANGE_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> paymentStatusChangeByHotelBookingId(@PathVariable long bookingid) {
		log.info("paymentStatusChangeByHotelBookingId controller called");
		return hotelProxyController.paymentStatusChangeByHotelBookingId(bookingid);
	}

	@PostMapping(HotelConstant.BOOK_ROOM_BY_USERNAME)
	public ResponseEntity<HotelBookingDetails> bookRoom(@PathVariable String username,
			@RequestBody HotelBookingDetails bd) {
		log.info("bookRoom controller called");
		return hotelProxyController.bookRoom(username, bd);
	}

	@DeleteMapping(HotelConstant.DELETE_HOTEL_BOOKING_DETAILS_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<String> removeHotelBookingDetailsByHotelBookingId(@PathVariable long bookingid) {
		log.info("removeHotelBookingDetailsByHotelBookingId controller called");
		return hotelProxyController.removeHotelBookingDetailsByHotelBookingId(bookingid);
	}

	@PostMapping(HotelConstant.ADD_GUEST_BY_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> addGuest(@PathVariable long bookingid,
			@RequestBody List<HotelGuest> guest) {
		log.info("addGuest controller called");
		return hotelProxyController.addGuest(bookingid, guest);
	}

	@PutMapping(HotelConstant.CANCEL_PAYMENT_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> cancelPaymentByHotelBookingId(@PathVariable long id) {
		log.info("cancelPaymentByHotelBookingId controller called");
		return hotelProxyController.cancelPaymentByHotelBookingId(id);
	}

}
