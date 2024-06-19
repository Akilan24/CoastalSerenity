package com.roomservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roomservice.constant.RoomConstant;
import com.roomservice.entity.Room;
import com.roomservice.service.RoomServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(RoomConstant.ROOM)
public class RoomController {

	@Autowired
	RoomServiceImpl rservice;

	@PostMapping(RoomConstant.ADD_ROOM_BY_HOTEL_ID)
	public ResponseEntity<Room> addRoomByHotelId(@PathVariable long hid, @RequestBody @Valid Room room) throws Exception {
		log.info("addRoomByHotelId controller called");
		return new ResponseEntity<>(rservice.addRoomDetailsByHotelId(hid, room), HttpStatus.OK);

	}

	@PutMapping(RoomConstant.UPDATE_ROOM)
	public ResponseEntity<Room> updateRoom(@RequestBody @Valid Room room) {
		log.info("updateRoom controller called");
		return new ResponseEntity<>(rservice.updateRoomDetails(room), HttpStatus.OK);
	}

	@DeleteMapping(RoomConstant.DELETE_ROOM_BY_HOTEL_ID)
	public ResponseEntity<String> deleteRoomByRoomId(@PathVariable Integer id) {
		log.info("deleteRoomByRoomId controller called");
		return new ResponseEntity<>(rservice.removeRoomDetailsByRoomId(id), HttpStatus.OK);
	}

	@GetMapping(RoomConstant.GET_ALL_ROOM)
	public ResponseEntity<List<Room>> getAllRoom() {
		log.info("getAllRoom controller called");
		return new ResponseEntity<>(rservice.showAllRoomDetails(), HttpStatus.OK);
	}

	@GetMapping(RoomConstant.GET_ROOM_BY_ROOM_ID)
	public ResponseEntity<Room> getRoomByRoomId(@PathVariable Integer id) {
		log.info("getRoomByRoomId controller called");
		return new ResponseEntity<>(rservice.showRoomDetailsByRoomId(id), HttpStatus.OK);
	}

	@GetMapping(RoomConstant.GET_ROOMS_BY_HOTEL_ID)
	public ResponseEntity<List<Room>> getRoomsByHotelId(@PathVariable Integer hid) {
		log.info("getRoomsByHotelId controller called");
		return new ResponseEntity<>(rservice.showAllRoomDetailsByHotelId(hid), HttpStatus.OK);
	}
 
	@GetMapping(RoomConstant.GET_ROOMS_BY_ROOM_PRICE)
	public ResponseEntity<List<Room>> getRoomsByRoomPrice(@PathVariable Double price) {
		log.info("getRoomsByRoomPrice controller called");
		return new ResponseEntity<>(rservice.showRoomDetailByRoomPrice(price), HttpStatus.OK);
	}

	@GetMapping(RoomConstant.GET_ROOMS_BY_ROOM_TYPE)
	public ResponseEntity<List<Room>> getRoomsByRoomType(@PathVariable String type) {
		log.info("getRoomsByRoomType controller called");
		return new ResponseEntity<>(rservice.showRoomDetailByRoomType(type), HttpStatus.OK);
	}

}
