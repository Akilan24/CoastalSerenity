package com.hotelservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hotelservice.controller.HotelController;
import com.hotelservice.entity.Hotel;
import com.hotelservice.service.HotelService;

@ExtendWith(MockitoExtension.class)
class HotelControllerTest {

	@Mock
	HotelService hotelService;

	@InjectMocks
	HotelController hotelController;

	@Test
	void testGetHotels() {
		List<Hotel> hotels = new ArrayList<>();
		hotels.add(new Hotel(123456L, "Bangalore", "Hotel1", "123 Main Street", "Hotel Description",
				"hotel@example.com", "9876543210", "8765432109", "https://www.hotelwebsite.com", "image", null));
		hotels.add(new Hotel(123457L, "Bangalore", "Hotel1", "123 Main Street", "Hotel Description",
				"hotel@example.com", "9876543210", "8765432109", "https://www.hotelwebsite.com", "image", null));
		when(hotelService.getAllHotel()).thenReturn(hotels);

		ResponseEntity<List<Hotel>> response = hotelController.getAllHotel();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotels, response.getBody());
	}

	@Test
	void testGetHotelById() {
		Long id = 123456L;
		Hotel hotel = new Hotel(123456L, "Bangalore", "Hotel1", "123 Main Street", "Hotel Description",
				"hotel@example.com", "9876543210", "8765432109", "https://www.hotelwebsite.com", "image", null);

		when(hotelService.getHotelByHotelId(id)).thenReturn(hotel);

		ResponseEntity<Hotel> response = hotelController.getHotelByHotelId(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotel, response.getBody());
	}

	@Test
	void testGetHotelByHotelName() {
		String hotelName = "Hotel1";
		Hotel hotel = new Hotel(123456L, "Bangalore", "Hotel1", "123 Main Street", "Hotel Description",
				"hotel@example.com", "9876543210", "8765432109", "https://www.hotelwebsite.com", "image", null);

		when(hotelService.getHotelByHotelName(hotelName)).thenReturn(hotel);

		ResponseEntity<Hotel> response = hotelController.getHotelByHotelName(hotelName);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotel, response.getBody());
	}

	@Test
	void testGetHotelByCityName() {
		String cityName = "Bangalore";
		List<Hotel> hotels = new ArrayList<>();
		hotels.add(new Hotel(123456L, "Bangalore", "Hotel1", "123 Main Street", "Hotel Description",
				"hotel@example.com", "9876543210", "8765432109", "https://www.hotelwebsite.com", "image", null));
		hotels.add(new Hotel(123457L, "Bangalore", "Hotel1", "123 Main Street", "Hotel Description",
				"hotel@example.com", "9876543210", "8765432109", "https://www.hotelwebsite.com", "image", null));

		when(hotelService.getAllHotelByCity(cityName)).thenReturn(hotels);

		ResponseEntity<List<Hotel>> response = hotelController.getAllHotelByCity(cityName);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotels, response.getBody());
	}

	@Test
	void testAddHotel() throws Exception {
		Hotel hotel = new Hotel(123456L, "Bangalore", "Hotel1", "123 Main Street", "Hotel Description",
				"hotel@example.com", "9876543210", "8765432109", "https://www.hotelwebsite.com", "image", null);
		when(hotelService.addHotel(hotel)).thenReturn(hotel);

		ResponseEntity<Hotel> response = hotelController.addHotel(hotel);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotel, response.getBody());
	}

	@Test
	void testUpdateHotel() {
		Hotel hotel = new Hotel(123456, "Bangalore", "Hotel", "123 Main Street", "Hotel Description",
				"hotel@example.com", "9876543210", "8765432109", "https://www.hotelwebsite.com", "image", null);
		Hotel updatedhotel = new Hotel(123456, "Bangalore", "Hotel", "123 Main Street", "Hotel Description",
				"hotel@example.com", "9876548455", "8765474210", "https://www.hotelwebsite.com", "image", null);

		when(hotelService.updateHotel(hotel)).thenReturn(updatedhotel);

		ResponseEntity<Hotel> response = hotelController.updateHotel(hotel);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedhotel, response.getBody());
	}

	@Test
	void testDeleteHotel() {
		Long id = 123456L;
		when(hotelService.deleteHotelByHotelId(id)).thenReturn("Hotel deleted successfully");

		ResponseEntity<String> response = hotelController.deleteHotelByHotelId(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Hotel deleted successfully", response.getBody());
	}
}
