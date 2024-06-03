package com.roomservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.roomservice.entity.Room;

@Service
public interface RoomService {

	public Room addRoomDetails(long hid, Room room) throws Exception;

	public Room updateRoomDetails(Room room);

	public String removeRoomDetails(long roomId);

	public List<Room> showAllRoomDetails();

	public List<Room> showAllRoomDetailsByHotelId(long hid);

	public Room showRoomDetailsbyId(long roomId);

	public List<Room> showRoomDetailBytype(String type);

	public List<Room> showRoomDetailByPrice(double price);
}
