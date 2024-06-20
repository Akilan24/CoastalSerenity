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

import com.hotelservice.constant.HotelConstant;
import com.hotelservice.entity.Hotel;
import com.hotelservice.entity.HotelBookingDetails;
import com.hotelservice.entity.HotelGuest;
import com.hotelservice.entity.HotelRooms;
import com.hotelservice.service.HotelBookingDetailsService;
import com.hotelservice.service.HotelService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(HotelConstant.HOTEL)
public class HotelController {
	@Autowired
	private HotelService hotelService;

	@Autowired
	HotelBookingDetailsService hotelBookingDetailsService;

	@GetMapping(HotelConstant.GET_ALL_HOTEL)
	public ResponseEntity<List<Hotel>> getAllHotel() {
		log.info("getAllHotel controller called");
		return new ResponseEntity<>(hotelService.getAllHotel(), HttpStatus.OK);
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_NAMES_BY_CITY)
	public ResponseEntity<List<String>> getHotelNamesByCity(@PathVariable String city) {
		log.info("getHotelNamesByCity controller called");
		return new ResponseEntity<>(hotelService.getHotelNamesByCity(city), HttpStatus.OK);
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_CITY_NAMES)
	public ResponseEntity<Set<String>> getAllHotelCityNames() {
		log.info("getAllHotelCityNames controller called");
		return new ResponseEntity<>(hotelService.getAllHotelCityNames(), HttpStatus.OK);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BY_HOTEL_ID)
	public ResponseEntity<Hotel> getHotelByHotelId(@PathVariable Long id) {
		log.info("getHotelByHotelId controller called");
		return new ResponseEntity<>(hotelService.getHotelByHotelId(id), HttpStatus.OK);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BY_HOTEL_NAME)
	public ResponseEntity<Hotel> getHotelByHotelName(@PathVariable String hotelname) {
		log.info("getHotelByHotelName controller called");
		return new ResponseEntity<>(hotelService.getHotelByHotelName(hotelname), HttpStatus.OK);
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_BY_CITY_NAME)
	public ResponseEntity<List<Hotel>> getAllHotelByCity(@PathVariable String cityname) {
		log.info("getAllHotelByCity controller called");
		return new ResponseEntity<>(hotelService.getAllHotelByCity(cityname), HttpStatus.OK);
	}

	@PostMapping(HotelConstant.ADD_HOTEL)
	public ResponseEntity<Hotel> addHotel(@RequestBody @Valid Hotel htl) throws Exception {
		log.info("addHotel controller called");
		return new ResponseEntity<>(hotelService.addHotel(htl), HttpStatus.OK);
	}

	@PutMapping(HotelConstant.UPDATE_HOTEL)
	public ResponseEntity<Hotel> updateHotel(@RequestBody @Valid Hotel ht) {
		log.info("updateHotel controller called");
		return new ResponseEntity<>(hotelService.updateHotel(ht), HttpStatus.OK);

	}

	@DeleteMapping(HotelConstant.DELETE_HOTEL_BY_HOTEL_ID)
	public ResponseEntity<String> deleteHotelByHotelId(@PathVariable Long id) {
		log.info("deleteHotelByHotelId controller called");
		return new ResponseEntity<>(hotelService.deleteHotelByHotelId(id), HttpStatus.OK);
	}

	@GetMapping(HotelConstant.GET_ALL_HOTEL_BOOKING_DETAILS)
	public ResponseEntity<List<HotelBookingDetails>> listHotelBookingDetails() {
		log.info("listHotelBookingDetails controller called");
		return new ResponseEntity<>(hotelBookingDetailsService.showAllBookingDetails(), HttpStatus.OK);
	}

	@GetMapping(HotelConstant.GET_ALL_AVAILABLE_ROOMS)
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city,
			@PathVariable String roomtype, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate) {
		log.info("checkRoomAvailability controller called");
		return new ResponseEntity<>(hotelBookingDetailsService.AvailableRoom(roomtype, city, fromDate, ToDate),
				HttpStatus.OK);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_ID)
	public ResponseEntity<HotelBookingDetails> showHotelBookingDetailsByHotelBookingId(@PathVariable long bookingid) {
		log.info("showHotelBookingDetailsByHotelBookingId controller called");
		return new ResponseEntity<>(hotelBookingDetailsService.showHotelBookingDetailsByHotelBookingId(bookingid),
				HttpStatus.OK);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_USERNAME_AND_HOTELNAME)
	public ResponseEntity<HotelBookingDetails> showHotelBookingDetailsByUserNameAndHotelName(
			@PathVariable String username, @PathVariable String hotelName) {
		log.info("showHotelBookingDetailsByUserNameAndHotelName controller called");
		return new ResponseEntity<>(
				hotelBookingDetailsService.showHotelBookingDetailsByUserNameAndHotelName(username, hotelName),
				HttpStatus.OK);
	}

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<HotelBookingDetails>> showHotelBookingDetailsByUserName(@PathVariable String username) {
		log.info("showHotelBookingDetailsByUserName controller called");
		return new ResponseEntity<>(hotelBookingDetailsService.showHotelBookingDetailsByUserName(username),
				HttpStatus.OK);
	}

	@PutMapping(HotelConstant.PAYMENT_STATUS_CHANGE_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> paymentStatusChangeByHotelBookingId(@PathVariable long bookingid) {
		log.info("paymentStatusChangeByHotelBookingId controller called");
		return new ResponseEntity<>(hotelBookingDetailsService.paymentStatusChangeByHotelBookingId(bookingid),
				HttpStatus.OK);
	}

	@PostMapping(HotelConstant.BOOK_ROOM_BY_USERNAME)
	public ResponseEntity<HotelBookingDetails> bookRoom(@PathVariable String username,
			@RequestBody HotelBookingDetails bd) {
		log.info("bookRoom controller called");
		return new ResponseEntity<>(hotelBookingDetailsService.bookRoom(username, bd), HttpStatus.OK);
	}

	@DeleteMapping(HotelConstant.DELETE_HOTEL_BOOKING_DETAILS_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<String> removeHotelBookingDetailsByHotelBookingId(@PathVariable long bookingid) {
		log.info("removeHotelBookingDetailsByHotelBookingId controller called");
		return new ResponseEntity<>(hotelBookingDetailsService.removeHotelBookingDetailsByHotelBookingId(bookingid),
				HttpStatus.OK);
	}

	@PostMapping(HotelConstant.ADD_GUEST_BY_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> addGuest(@PathVariable long bookingid,
			@RequestBody List<HotelGuest> guest) {
		log.info("addGuest controller called");
		return new ResponseEntity<>(hotelBookingDetailsService.addGuest(bookingid, guest), HttpStatus.OK);
	}

	@PutMapping(HotelConstant.CANCEL_PAYMENT_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> cancelPaymentByHotelBookingId(@PathVariable long id) {
		log.info("cancelPaymentByHotelBookingId controller called");
		return new ResponseEntity<>(hotelBookingDetailsService.cancelPaymentByHotelBookingId(id), HttpStatus.OK);
	}

}
