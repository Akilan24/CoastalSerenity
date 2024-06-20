package com.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.constant.RoomConstant;
import com.authservice.proxyentity.room.Room;
import com.authservice.proxyentity.room.RoomProxyController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(RoomConstant.ROOM)
@CrossOrigin(RoomConstant.CROSS_ORIGIN)
public class RoomController {

	@Autowired
	RoomProxyController roomProxy;

	@PostMapping(RoomConstant.ADD_ROOM_BY_HOTEL_ID)
	public ResponseEntity<Room> addRoomByHotelId(@PathVariable long hid, @RequestBody @Valid Room room)
			throws Exception {
		log.info("addRoomByHotelId controller called");
		return roomProxy.addRoomByHotelId(hid, room);

	}

	@PutMapping(RoomConstant.UPDATE_ROOM)
	public ResponseEntity<Room> updateRoom(@RequestBody @Valid Room room) {
		log.info("updateRoom controller called");
		return roomProxy.updateRoom(room);
	}

	@DeleteMapping(RoomConstant.DELETE_ROOM_BY_HOTEL_ID)
	public ResponseEntity<String> deleteRoomByRoomId(@PathVariable Integer id) {
		log.info("deleteRoomByRoomId controller called");
		return roomProxy.deleteRoomByRoomId(id);
	}

	@GetMapping(RoomConstant.GET_ALL_ROOM)
	public ResponseEntity<List<Room>> getAllRoom() {
		log.info("getAllRoom controller called");
		return roomProxy.getAllRoom();
	}

	@GetMapping(RoomConstant.GET_ROOM_BY_ROOM_ID)
	public ResponseEntity<Room> getRoomByRoomId(@PathVariable Integer id) {
		log.info("getRoomByRoomId controller called");
		return roomProxy.getRoomByRoomId(id);
	}

	@GetMapping(RoomConstant.GET_ROOMS_BY_HOTEL_ID)
	public ResponseEntity<List<Room>> getRoomsByHotelId(@PathVariable Integer hid) {
		log.info("getRoomsByHotelId controller called");
		return roomProxy.getRoomsByHotelId(hid);
	}

	@GetMapping(RoomConstant.GET_ROOMS_BY_ROOM_PRICE)
	public ResponseEntity<List<Room>> getRoomsByRoomPrice(@PathVariable Double price) {
		log.info("getRoomsByRoomPrice controller called");
		return roomProxy.getRoomsByRoomPrice(price);
	}

	@GetMapping(RoomConstant.GET_ROOMS_BY_ROOM_TYPE)
	public ResponseEntity<List<Room>> getRoomsByRoomType(@PathVariable String type) {
		log.info("getRoomsByRoomType controller called");
		return roomProxy.getRoomsByRoomType(type);
	}

}
