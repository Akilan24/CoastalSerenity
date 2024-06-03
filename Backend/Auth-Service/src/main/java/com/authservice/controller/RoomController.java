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

import com.authservice.ProxyEntity.Room;
import com.authservice.proxyController.RoomProxyController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/HMA/Room")
@CrossOrigin("http://localhost:5173")
public class RoomController {

	@Autowired
	RoomProxyController roomProxy;

	@PostMapping("/addroom/{hid}")
	public ResponseEntity<Room> addroom(@PathVariable long hid, @RequestBody @Valid Room room) throws Exception {
		return roomProxy.addroom(hid, room);

	}

	@PutMapping("/updateroom")
	public ResponseEntity<Room> updateroom(@RequestBody @Valid Room room) {
		return roomProxy.updateroom(room);
	}

	@DeleteMapping("/deleteroombyid/{id}")
	public ResponseEntity<String> deleteroom(@PathVariable Integer id) {
		return roomProxy.deleteroom(id);
	}

	@GetMapping("/getallroom")
	public ResponseEntity<List<Room>> getall() {
		return roomProxy.getall();
	}

	@GetMapping("/getroombyid/{id}")
	public ResponseEntity<Room> getroom(@PathVariable Integer id) {
		return roomProxy.getroom(id);
	}

	@GetMapping("/getroombyhotelid/{hid}")
	public ResponseEntity<List<Room>> getroombyhid(@PathVariable Integer hid) {
		return roomProxy.getroombyhid(hid);
	}

	@GetMapping("/getroombyprice/{price}")
	public ResponseEntity<List<Room>> getroom(@PathVariable Double price) {
		return roomProxy.getroom(price);
	}

	@GetMapping("/getroombyroomtype/{type}")
	public ResponseEntity<List<Room>> getroombytype(@PathVariable String type) {
		return roomProxy.getroombytype(type);
	}

}
