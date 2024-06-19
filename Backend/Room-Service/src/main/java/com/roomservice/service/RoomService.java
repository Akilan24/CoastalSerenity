package com.roomservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.roomservice.entity.Room;

@Service
public interface RoomService {

	public Room addRoomDetailsByHotelId(long hid, Room room) throws Exception;

	public Room updateRoomDetails(Room room);

	public String removeRoomDetailsByRoomId(long roomId);

	public List<Room> showAllRoomDetails();

	public List<Room> showAllRoomDetailsByHotelId(long hid);

	public Room showRoomDetailsByRoomId(long roomId);

	public List<Room> showRoomDetailByRoomType(String type);

	public List<Room> showRoomDetailByRoomPrice(double price);
}
