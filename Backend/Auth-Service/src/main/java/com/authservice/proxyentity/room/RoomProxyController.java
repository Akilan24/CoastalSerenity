package com.authservice.proxyentity.room;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.constant.RoomConstant;

import jakarta.validation.Valid;

@FeignClient(name = RoomConstant.SERVICE, url = RoomConstant.URL)
public interface RoomProxyController {

	@PostMapping(RoomConstant.ADD_ROOM_BY_HOTEL_ID)
	public ResponseEntity<Room> addRoomByHotelId(@PathVariable long hid, @RequestBody @Valid Room room)
			throws Exception;

	@PutMapping(RoomConstant.UPDATE_ROOM)
	public ResponseEntity<Room> updateRoom(@RequestBody @Valid Room room);

	@DeleteMapping(RoomConstant.DELETE_ROOM_BY_HOTEL_ID)
	public ResponseEntity<String> deleteRoomByRoomId(@PathVariable Integer id);

	@GetMapping(RoomConstant.GET_ALL_ROOM)
	public ResponseEntity<List<Room>> getAllRoom();

	@GetMapping(RoomConstant.GET_ROOM_BY_ROOM_ID)
	public ResponseEntity<Room> getRoomByRoomId(@PathVariable Integer id);

	@GetMapping(RoomConstant.GET_ROOMS_BY_HOTEL_ID)
	public ResponseEntity<List<Room>> getRoomsByHotelId(@PathVariable Integer hid);

	@GetMapping(RoomConstant.GET_ROOMS_BY_ROOM_PRICE)
	public ResponseEntity<List<Room>> getRoomsByRoomPrice(@PathVariable Double price);

	@GetMapping(RoomConstant.GET_ROOMS_BY_ROOM_TYPE)
	public ResponseEntity<List<Room>> getRoomsByRoomType(@PathVariable String type);

}
