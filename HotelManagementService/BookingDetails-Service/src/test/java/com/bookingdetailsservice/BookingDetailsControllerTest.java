package com.bookingdetailsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookingdetailsservice.controller.BookingDetailsController;
import com.bookingdetailsservice.entity.BookingDetails;
import com.bookingdetailsservice.repository.BookingDetailsRepository;
import com.bookingdetailsservice.service.BookingDetailsService;

@ExtendWith(MockitoExtension.class)
class BookingDetailsControllerTest {

	@Mock
	private BookingDetailsService bookingDetailsService;
	
	@Mock
	private BookingDetailsRepository bookingDetailsRepo;

	@InjectMocks
	private BookingDetailsController bookingDetailsController;

	private BookingDetails bookingDetails;

	@BeforeEach
	void setUp() {
		 bookingDetails = new BookingDetails(123456,
			    "John Doe",
			    101,
			    "HotelName",
			    new Date(),
			    new Date(),
			    2,
			    1,
			    5000.0,
			    "Payment done"
			);

		
	}

	@Test
	void testListBookingDetails() {
		List<BookingDetails> bookingDetailsList = new ArrayList<>();
		bookingDetailsList.add(bookingDetails);
		when(bookingDetailsService.showAllBookingDetails()).thenReturn(bookingDetailsList);
		ResponseEntity<List<BookingDetails>> response = bookingDetailsController.listBookingDetails();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(bookingDetailsList, response.getBody());
	}


	@Test
	void testGetBookingDetails() {
		when(bookingDetailsService.showBookingDetailsbyId(123456)).thenReturn(bookingDetails);
		ResponseEntity<BookingDetails> response = bookingDetailsController.getBookingDetails(123456);
		assertEquals(bookingDetails, response.getBody());
	}

	@Test
	void testPaymentStatusChange() {
		when(bookingDetailsService.paymentstatuschange(123456)).thenReturn(bookingDetails);
		ResponseEntity<BookingDetails> response = bookingDetailsController.paymentstatuschange(123456);
		assertEquals(bookingDetails, response.getBody());
	}

	@Test
	void testBookRoom() {
		when(bookingDetailsService.BookRoom(anyString(), any(BookingDetails.class)))
				.thenReturn(bookingDetails);
		ResponseEntity<BookingDetails> response = bookingDetailsController.bookroom("user123", bookingDetails);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(bookingDetails, response.getBody());
	}

	@Test
	void testRemove() {	
		when(bookingDetailsService.removeBookingDetails(123456)).thenReturn("Booking details removed successfully");
		ResponseEntity<String> response = bookingDetailsController.remove(123456);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Booking details removed successfully", response.getBody());
	}
}
