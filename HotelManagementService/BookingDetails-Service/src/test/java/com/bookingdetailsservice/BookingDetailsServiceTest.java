package com.bookingdetailsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookingdetailsservice.entity.BookingDetails;
import com.bookingdetailsservice.exception.BookingDetailsNotFoundException;
import com.bookingdetailsservice.externalclass.Hotel;
import com.bookingdetailsservice.externalclass.Room;
import com.bookingdetailsservice.proxy.RoomProxy;
import com.bookingdetailsservice.repository.BookingDetailsRepository;
import com.bookingdetailsservice.repository.HotelRepository;
import com.bookingdetailsservice.service.BookingDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookingDetailsServiceTest {

	@Mock
	private BookingDetailsRepository bookingDetailsRepository;

	@Mock
	private RoomProxy roomProxy;

	@Mock
	private HotelRepository hotelRepo;

	@InjectMocks
	private BookingDetailsServiceImpl bookingDetailsService;

	private BookingDetails bookingDetails;

	@BeforeEach
	void setUp() {
		bookingDetails = new BookingDetails();
		// Set up booking details object
	}

	@Test
	void testBookRoom() {
		String userId = "user123";
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", null);

		Room room = new Room(101010, 201, 4000.00, "Deluxe");
		hotel.setRooms(Arrays.asList(room));
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setRoomno(201);
		bookingDetails.setHotelname(hotel.getHotelName());
		when(bookingDetailsRepository.findAll()).thenReturn(Arrays.asList());
		when(hotelRepo.findAll()).thenReturn(Arrays.asList(hotel));
		when(bookingDetailsRepository.save(any(BookingDetails.class))).thenReturn(bookingDetails);

		assertEquals(bookingDetails, bookingDetailsService.BookRoom(userId, bookingDetails));
	}

	@Test
	void testRemoveBookingDetails() {
		when(bookingDetailsRepository.findByBookingId(123456)).thenReturn(Optional.of(bookingDetails));

		String result = bookingDetailsService.removeBookingDetails(123456);

		assertEquals("Booking details are deleted", result);
	}

	@Test
	void testShowAllBookingDetails() {
		List<BookingDetails> bookingDetailsList = new ArrayList<>();
		bookingDetailsList.add(bookingDetails);
		when(bookingDetailsRepository.findAll()).thenReturn(bookingDetailsList);

		List<BookingDetails> result = bookingDetailsService.showAllBookingDetails();

		assertEquals(1, result.size());
		assertEquals(bookingDetails, result.get(0));
	}

	@Test
	void testShowBookingDetailsbyId() {
		when(bookingDetailsRepository.findByBookingId(123456)).thenReturn(Optional.of(bookingDetails));

		BookingDetails result = bookingDetailsService.showBookingDetailsbyId(123456);

		assertEquals(bookingDetails, result);
	}

	@Test
	void testPaymentStatusChange() {
		long bookingId = 123456;
		BookingDetails bookingDetails = new BookingDetails();

		when(bookingDetailsRepository.findByBookingId(bookingId)).thenReturn(Optional.of(bookingDetails));
		when(bookingDetailsRepository.save(any(BookingDetails.class))).thenReturn(bookingDetails);

		BookingDetails result = bookingDetailsService.paymentstatuschange(bookingId);

		assertEquals("Payment done", result.getPaymentStatus());

	}

	@Test
	void testPaymentStatusChange_BookingNotFound() {
		int bookingId = 1;

		when(bookingDetailsRepository.findByBookingId(bookingId)).thenReturn(Optional.empty());

		assertThrows(BookingDetailsNotFoundException.class, () -> bookingDetailsService.paymentstatuschange(bookingId));
	}
}
