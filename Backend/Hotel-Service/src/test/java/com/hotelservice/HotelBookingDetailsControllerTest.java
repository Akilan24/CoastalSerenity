package com.hotelservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hotelservice.controller.HotelController;
import com.hotelservice.entity.HotelBookingDetails;
import com.hotelservice.repository.HotelBookingDetailsRepository;
import com.hotelservice.service.HotelBookingDetailsService;

@ExtendWith(MockitoExtension.class)
class HotelhotelControllerTest {

	@Mock
	private HotelBookingDetailsService bookingDetailsService;

	@Mock
	private HotelBookingDetailsRepository bookingDetailsRepo;

	@InjectMocks
	private HotelController hotelController;

	private HotelBookingDetails bookingDetails;

	@BeforeEach
	void setUp() {
		bookingDetails = new HotelBookingDetails(123456, "John Doe", 101, "Grand Hotel", new Date(), new Date(), 250.50,
				"john@example.com", "1234567890", "123 Main Street, City", LocalDateTime.now(), null, "Pending",
				"john123");
	}

	@Test
	void testListBookingDetails() {
		List<HotelBookingDetails> bookingDetailsList = new ArrayList<>();
		bookingDetailsList.add(bookingDetails);
		when(bookingDetailsService.showAllBookingDetails()).thenReturn(bookingDetailsList);
		ResponseEntity<List<HotelBookingDetails>> response = hotelController.listHotelBookingDetails();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(bookingDetailsList, response.getBody());
	}

	@Test
	void testGetBookingDetails() {
		when(bookingDetailsService.showHotelBookingDetailsByHotelBookingId(123456)).thenReturn(bookingDetails);
		ResponseEntity<HotelBookingDetails> response = hotelController.showHotelBookingDetailsByHotelBookingId(123456);
		assertEquals(bookingDetails, response.getBody());
	}

	@Test
	void testPaymentStatusChange() {
		when(bookingDetailsService.paymentStatusChangeByHotelBookingId(123456)).thenReturn(bookingDetails);
		ResponseEntity<HotelBookingDetails> response = hotelController.paymentStatusChangeByHotelBookingId(123456);
		assertEquals(bookingDetails, response.getBody());
	}

	@Test
	void testBookRoom() {
		when(bookingDetailsService.bookRoom(anyString(), any(HotelBookingDetails.class))).thenReturn(bookingDetails);
		ResponseEntity<HotelBookingDetails> response = hotelController.bookRoom("user123", bookingDetails);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(bookingDetails, response.getBody());
	}

	@Test
	void testRemove() {
		when(bookingDetailsService.removeHotelBookingDetailsByHotelBookingId(123456))
				.thenReturn("Booking details removed successfully");
		ResponseEntity<String> response = hotelController.removeHotelBookingDetailsByHotelBookingId(123456);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Booking details removed successfully", response.getBody());
	}
}
