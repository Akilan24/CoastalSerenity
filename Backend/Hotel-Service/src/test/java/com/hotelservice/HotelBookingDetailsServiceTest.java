package com.hotelservice;

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

import com.hotelservice.entity.HotelBookingDetails;
import com.hotelservice.entity.Hotel;
import com.hotelservice.exception.HotelBookingDetailsNotFoundException;
import com.hotelservice.externalclass.Room;
import com.hotelservice.proxy.RoomProxy;
import com.hotelservice.repository.HotelBookingDetailsRepository;
import com.hotelservice.repository.HotelRepository;
import com.hotelservice.service.HotelBookingDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HotelBookingDetailsServiceTest {

	@Mock
	private HotelBookingDetailsRepository bookingDetailsRepository;

	@Mock
	private RoomProxy roomProxy;

	@Mock
	private HotelRepository hotelRepo;

	@InjectMocks
	private HotelBookingDetailsServiceImpl bookingDetailsService;

	private HotelBookingDetails bookingDetails;

	@BeforeEach
	void setUp() {
		bookingDetails = new HotelBookingDetails();
		// Set up booking details object
	}

	@Test
	void testBookRoom() {
		String userId = "user123";
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", "image", null);

		Room room = new Room(101010, 201, 4000.00, "Deluxe", "image");
		hotel.setRooms(Arrays.asList(room));
		HotelBookingDetails bookingDetails = new HotelBookingDetails();
		bookingDetails.setRoomno(201);
		bookingDetails.setHotelname(hotel.getHotelName());
		when(bookingDetailsRepository.findAll()).thenReturn(Arrays.asList());
		when(hotelRepo.findAll()).thenReturn(Arrays.asList(hotel));
		when(bookingDetailsRepository.save(any(HotelBookingDetails.class))).thenReturn(bookingDetails);

		assertEquals(bookingDetails, bookingDetailsService.BookRoom(userId, bookingDetails));
	}

	@Test
	void testRemoveBookingDetails() {
		when(bookingDetailsRepository.findByBookingid(123456)).thenReturn(Optional.of(bookingDetails));

		String result = bookingDetailsService.removeBookingDetails(123456);

		assertEquals("Booking details are deleted", result);
	}

	@Test
	void testShowAllBookingDetails() {
		List<HotelBookingDetails> bookingDetailsList = new ArrayList<>();
		bookingDetailsList.add(bookingDetails);
		when(bookingDetailsRepository.findAll()).thenReturn(bookingDetailsList);

		List<HotelBookingDetails> result = bookingDetailsService.showAllBookingDetails();

		assertEquals(1, result.size());
		assertEquals(bookingDetails, result.get(0));
	}

	@Test
	void testShowBookingDetailsbyId() {
		when(bookingDetailsRepository.findByBookingid(123456)).thenReturn(Optional.of(bookingDetails));

		HotelBookingDetails result = bookingDetailsService.showBookingDetailsbyId(123456);

		assertEquals(bookingDetails, result);
	}

	@Test
	void testPaymentStatusChange() {
		long bookingId = 123456;
		HotelBookingDetails bookingDetails = new HotelBookingDetails();

		when(bookingDetailsRepository.findByBookingid(bookingId)).thenReturn(Optional.of(bookingDetails));
		when(bookingDetailsRepository.save(any(HotelBookingDetails.class))).thenReturn(bookingDetails);

		HotelBookingDetails result = bookingDetailsService.paymentstatuschange(bookingId);

		assertEquals("Payment done", result.getPaymentStatus());

	}

	@Test
	void testPaymentStatusChange_BookingNotFound() {
		int bookingId = 1;

		when(bookingDetailsRepository.findByBookingid(bookingId)).thenReturn(Optional.empty());

		assertThrows(HotelBookingDetailsNotFoundException.class,
				() -> bookingDetailsService.paymentstatuschange(bookingId));
	}
}
