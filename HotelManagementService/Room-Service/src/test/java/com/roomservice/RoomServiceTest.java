package com.roomservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.roomservice.entity.Hotel;
import com.roomservice.entity.Room;
import com.roomservice.exception.RoomDetailsNotFoundException;
import com.roomservice.repository.RoomRepository;
import com.roomservice.service.RoomServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
	@Mock
	private RoomRepository roomRepository;

	@InjectMocks
	private RoomServiceImpl roomService;

	@Test
	void testAddRoomDetails_Success() throws Exception {
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", null);
		Room room = new Room(101010, 201, 4000.00, "Deluxe");

		when(roomRepository.save(room)).thenReturn(room);

		assertEquals(room, roomService.addRoomDetails(hotel.getHotelId(), room));
		verify(roomRepository, times(1)).save(room);
	}

	@Test
	void testAddRoomDetails_Exception() {
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", null);

		Room room = new Room(101010, 201, 4000.00, "Deluxe");
		when(roomRepository.save(room)).thenThrow(new RuntimeException());

		assertThrows(Exception.class, () -> roomService.addRoomDetails(hotel.getHotelId(), room));
	}

	@Test
	void testUpdateRoomDetails_Success() {
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", null);

		Room room = new Room(101010, 201, 4000.00, "Deluxe");
		Optional<Room> optionalRoom = Optional.of(room);

		when(roomRepository.findByRoomId(101010)).thenReturn(optionalRoom);
		when(roomRepository.save(room)).thenReturn(room);

		Room result = roomService.updateRoomDetails(room);

		assertEquals(room, result);
	}

	@Test
	void testUpdateRoomDetails_NotFound() {
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", null);

		Room room = new Room(101010, 201, 4000.00, "Deluxe");
		when(roomRepository.findByRoomId(101010)).thenReturn(Optional.empty());

		assertThrows(RoomDetailsNotFoundException.class, () -> roomService.updateRoomDetails(room));
	}

	@Test
	void testUpdateRoomDetails() {
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", null);

		Room room = new Room(101010, 201, 4000.00, "Deluxe");
		Room existingRoom = new Room(101010, 201, 5000.00, "Deluxe");
		when(roomRepository.findByRoomId(room.getRoomId())).thenReturn(Optional.of(existingRoom));
		when(roomService.updateRoomDetails(room)).thenReturn(existingRoom);

		assertEquals(existingRoom, roomService.updateRoomDetails(room));
	}

	@Test
	void testShowAllRoomDetails_Success() {
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", null);

		List<Room> rooms = Arrays.asList(new Room(101010, 201, 4000.00, "Deluxe"),
				new Room(101011, 202, 5000.00, "Deluxe"));

		when(roomRepository.findAll()).thenReturn(rooms);

		List<Room> result = roomService.showAllRoomDetails();

		assertEquals(2, result.size());
		assertEquals(rooms, result);
	}

	@Test
	void testShowAllRoomDetails_NotFound() {
		when(roomRepository.findAll()).thenReturn(Arrays.asList());

		assertThrows(RoomDetailsNotFoundException.class, () -> roomService.showAllRoomDetails());
	}

	@Test
	void testShowRoomDetailsById() {
		long roomId = 101010;
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", null);

		Room room = new Room(101010, 201, 4000.00, "Deluxe");
		when(roomRepository.findByRoomId(roomId)).thenReturn(Optional.of(room));
		Room result = roomService.showRoomDetailsbyId(roomId);
		assertEquals(room, result);
	}

	@Test
	void testShowRoomDetailsById_RoomNotFound() {
		long roomId = 101010;
		when(roomRepository.findByRoomId(roomId)).thenReturn(Optional.empty());
		assertThrows(RoomDetailsNotFoundException.class, () -> roomService.showRoomDetailsbyId(roomId));
	}

	@Test
	void testShowAllRoomDetailsByHotelId() {
		long hotelId = 101010;
		Hotel hotel = new Hotel(101010, "City", "HotelName", "Address", "Description", "email@example.com",
				"9876543210", "9876543211", "http://website.com", null);

		List<Room> rooms = Arrays.asList(new Room(101010, 201, 4000.00, "Deluxe"),
				new Room(101011, 202, 5000.00, "Deluxe"));

		when(roomRepository.findAll()).thenReturn(rooms);

		List<Room> result = roomService.showAllRoomDetailsByHotelId(hotelId);

		assertEquals(rooms, result);
	}

	@Test
	void testShowAllRoomDetailsByHotelId_NoRoomsFound() {
		long hotelId = 101010;

		when(roomRepository.findAll()).thenReturn(Arrays.asList());

		assertThrows(RoomDetailsNotFoundException.class, () -> roomService.showAllRoomDetailsByHotelId(hotelId));

	}

	void testShowRoomDetailBytype() {
		String type = "Single";
		Room room1 = new Room(101010, 201, 4000.00, "Deluxe");
		Room room2 = new Room(101011, 201, 5000.00, "Single");
		List<Room> rooms = Arrays.asList(room1, room2);
		when(roomRepository.findAll()).thenReturn(
				rooms.stream().filter(n -> n.getRoomtype().equalsIgnoreCase(type)).collect(Collectors.toList()));

		List<Room> result = roomService.showRoomDetailBytype(type);

		assertEquals(1, result.size());
		assertEquals(room1, result.get(0));
	}

	@Test
	void testShowRoomDetailBytype_RoomNotFound() {
		String type = "Single";
		when(roomRepository.findAll()).thenReturn(Arrays.asList());

		assertThrows(RoomDetailsNotFoundException.class, () -> roomService.showRoomDetailBytype(type));

	}

	@Test
	void testShowRoomDetailByPrice() {
		double price = 5000.0;
		Room room1 = new Room(101010, 201, 4000.00, "Deluxe");
		Room room2 = new Room(101011, 202, 5000.00, "Deluxe");
		List<Room> rooms = Arrays.asList(room1, room2);
		when(roomRepository.findAll())
				.thenReturn(rooms.stream().filter(n -> n.getRate_per_day() == price).collect(Collectors.toList()));

		List<Room> result = roomService.showRoomDetailByPrice(price);

		assertEquals(1, result.size());
		assertEquals(room2, result.get(0));
	}

	@Test
	void testShowRoomDetailByPrice_NoRoomsFound() {
		double price = 5000.0;
		when(roomRepository.findAll()).thenReturn(Arrays.asList());

		assertThrows(RoomDetailsNotFoundException.class, () -> roomService.showRoomDetailByPrice(price));

	}
}
