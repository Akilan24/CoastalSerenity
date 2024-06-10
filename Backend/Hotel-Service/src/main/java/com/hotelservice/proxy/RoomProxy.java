package com.hotelservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotelservice.externalclass.Room;

@FeignClient(name = "room-service", url = "http://localhost:8083/Room")
public interface RoomProxy {

	@GetMapping("/getbyid/{id}")
	public Room getroom(@PathVariable Integer id);

	@GetMapping("/getallroom")
	public ResponseEntity<List<Room>> getall();
}
