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

import com.authservice.constant.HotelConstant;

import jakarta.validation.Valid;

@FeignClient(name = HotelConstant.SERVICE, url = HotelConstant.URL)
public interface HotelProxyController {

	@GetMapping(HotelConstant.GET_ALL_HOTEL)
	public ResponseEntity<List<Hotel>> getAllHotel();

	@GetMapping(HotelConstant.GET_ALL_HOTEL_NAMES_BY_CITY)
	public ResponseEntity<List<String>> getHotelNamesByCity(@PathVariable String city);

	@GetMapping(HotelConstant.GET_ALL_HOTEL_CITY_NAMES)
	public ResponseEntity<Set<String>> getAllHotelCityNames();

	@GetMapping(HotelConstant.GET_HOTEL_BY_HOTEL_ID)
	public ResponseEntity<Hotel> getHotelByHotelId(@PathVariable Long id);

	@GetMapping(HotelConstant.GET_HOTEL_BY_HOTEL_NAME)
	public ResponseEntity<Hotel> getHotelByHotelName(@PathVariable String hotelname);

	@GetMapping(HotelConstant.GET_ALL_HOTEL_BY_CITY_NAME)
	public ResponseEntity<List<Hotel>> getAllHotelByCity(@PathVariable String cityname);

	@PostMapping(HotelConstant.ADD_HOTEL)
	public ResponseEntity<Hotel> addHotel(@RequestBody @Valid Hotel htl) throws Exception;

	@PutMapping(HotelConstant.UPDATE_HOTEL)
	public ResponseEntity<Hotel> updateHotel(@RequestBody @Valid Hotel ht);

	@DeleteMapping(HotelConstant.DELETE_HOTEL_BY_HOTEL_ID)
	public ResponseEntity<String> deleteHotelByHotelId(@PathVariable Long id);

	@GetMapping(HotelConstant.GET_ALL_HOTEL_BOOKING_DETAILS)
	public ResponseEntity<List<HotelBookingDetails>> listHotelBookingDetails();

	@GetMapping(HotelConstant.GET_ALL_AVAILABLE_ROOMS)
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city,
			@PathVariable String roomtype, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate);

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_ID)
	public ResponseEntity<HotelBookingDetails> showHotelBookingDetailsByHotelBookingId(@PathVariable long bookingid);

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_USERNAME_AND_HOTELNAME)
	public ResponseEntity<HotelBookingDetails> showHotelBookingDetailsByUserNameAndHotelName(
			@PathVariable String username, @PathVariable String hotelName);

	@GetMapping(HotelConstant.GET_HOTEL_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<HotelBookingDetails>> showHotelBookingDetailsByUserName(@PathVariable String username);

	@PutMapping(HotelConstant.PAYMENT_STATUS_CHANGE_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> paymentStatusChangeByHotelBookingId(@PathVariable long bookingid);

	@PostMapping(HotelConstant.BOOK_ROOM_BY_USERNAME)
	public ResponseEntity<HotelBookingDetails> bookRoom(@PathVariable String username,
			@RequestBody HotelBookingDetails bd);

	@DeleteMapping(HotelConstant.DELETE_HOTEL_BOOKING_DETAILS_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<String> removeHotelBookingDetailsByHotelBookingId(@PathVariable long bookingid);

	@PostMapping(HotelConstant.ADD_GUEST_BY_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> addGuest(@PathVariable long bookingid,
			@RequestBody List<HotelGuest> guest);

	@PutMapping(HotelConstant.CANCEL_PAYMENT_BY_HOTEL_BOOKING_ID)
	public ResponseEntity<HotelBookingDetails> cancelPaymentByHotelBookingId(@PathVariable long id);

}
