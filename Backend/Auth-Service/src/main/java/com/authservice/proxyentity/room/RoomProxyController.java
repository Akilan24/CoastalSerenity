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

import com.authservice.proxyentity.room.Room;

import jakarta.validation.Valid;

@FeignClient(name = "ROOM-SERVICE", url = "http://localhost:8083/Room")
public interface RoomProxyController {

	@PostMapping("/addroom/{hid}")
	public ResponseEntity<Room> addroom(@PathVariable long hid, @RequestBody @Valid Room room) throws Exception;

	@PutMapping("/updateroom")
	public ResponseEntity<Room> updateroom(@RequestBody @Valid Room room);

	@DeleteMapping("/deleteroombyid/{id}")
	public ResponseEntity<String> deleteroom(@PathVariable Integer id);

	@GetMapping("/getallroom")
	public ResponseEntity<List<Room>> getall();

	@GetMapping("/getroombyid/{id}")
	public ResponseEntity<Room> getroom(@PathVariable Integer id);

	@GetMapping("/getroombyhotelid/{hid}")
	public ResponseEntity<List<Room>> getroombyhid(@PathVariable Integer hid);

	@GetMapping("/getroombyprice/{price}")
	public ResponseEntity<List<Room>> getroom(@PathVariable Double price);

	@GetMapping("/getroombyroomtype/{type}")
	public ResponseEntity<List<Room>> getroombytype(@PathVariable String type);

}
