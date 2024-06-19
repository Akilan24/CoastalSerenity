package com.roomservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.roomservice.controller.RoomController;
import com.roomservice.entity.Hotel;
import com.roomservice.entity.Room;
import com.roomservice.repository.RoomRepository;
import com.roomservice.service.RoomServiceImpl;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

	@Mock
	private RoomServiceImpl roomService;

	@Mock
	private RoomRepository roomRepo;
	@InjectMocks
	private RoomController roomController;

	@Test
	void testAddRoom() throws Exception {
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", "image", null);
		Room room = new Room(101010, 201, 4000.00, "Deluxe", "image");
		when(roomService.addRoomDetailsByHotelId(hotel.getHotelId(), room)).thenReturn(room);

		ResponseEntity<Room> response = roomController.addRoomByHotelId(hotel.getHotelId(), room);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(room, response.getBody());
	}

	@Test
	void testUpdateRoom() {
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", "image", null);

		Room room = new Room(101010, 201, 4000.00, "Deluxe", "image");
		Room updatedroom = new Room(101010, 201, 5000.00, "Deluxe", "image");
		when(roomService.updateRoomDetails(room)).thenReturn(updatedroom);

		ResponseEntity<Room> response = roomController.updateRoom(room);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedroom, response.getBody());
	}

	@Test
	void testDeleteRoom() {
		Integer roomId = 201;
		when(roomService.removeRoomDetailsByRoomId(roomId)).thenReturn("Room deleted successfully");

		ResponseEntity<String> response = roomController.deleteRoomByRoomId(roomId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Room deleted successfully", response.getBody());
	}

	@Test
	void testGetAllRooms() {
		List<Room> rooms = Arrays.asList(new Room(201, 0, 4000.00, "Deluxe", "image"),
				new Room(202, 0, 5000.00, "Deluxe", "image"));
		when(roomService.showAllRoomDetails()).thenReturn(rooms);

		ResponseEntity<List<Room>> response = roomController.getAllRoom();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(rooms, response.getBody());
	}

	@Test
	void testGetRoomById() {
		Integer roomId = 201;
		Room room = new Room(201, roomId, 4000.00, "Deluxe", "image");
		when(roomService.showRoomDetailsByRoomId(roomId)).thenReturn(room);

		ResponseEntity<Room> response = roomController.getRoomByRoomId(roomId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(room, response.getBody());
	}

	@Test
	void testGetRoomByHotelId() {
		Integer hotelId = 201;
		List<Room> rooms = Arrays.asList(new Room(201, 0, 4000.00, "Deluxe", "image"),
				new Room(202, 0, 5000.00, "Deluxe", "image"));
		when(roomService.showAllRoomDetailsByHotelId(hotelId)).thenReturn(rooms);

		ResponseEntity<List<Room>> response = roomController.getRoomsByHotelId(hotelId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(rooms, response.getBody());
	}

	@Test
	void testGetRoomByPrice() {
		Double price = 5000.0;
		List<Room> rooms = Arrays.asList(new Room(201, 0, 4000.00, "Deluxe", "image"),
				new Room(202, 0, 5000.00, "Deluxe", "image"));
		when(roomService.showRoomDetailByRoomPrice(price)).thenReturn(rooms);

		ResponseEntity<List<Room>> response = roomController.getRoomsByRoomPrice(price);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(rooms, response.getBody());
	}

	@Test
	void testGetRoomByType() {
		String type = "Deluxe";
		List<Room> rooms = Arrays.asList(new Room(201, 0, 4000.00, "Deluxe", "image"),
				new Room(202, 0, 5000.00, "Deluxe", "image"));
		when(roomService.showRoomDetailByRoomType(type)).thenReturn(rooms);

		ResponseEntity<List<Room>> response = roomController.getRoomsByRoomType(type);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(rooms, response.getBody());
	}
}
