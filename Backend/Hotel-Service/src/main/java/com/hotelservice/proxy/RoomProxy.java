package com.hotelservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotelservice.constant.RoomProxyConstant;
import com.hotelservice.externalclass.Room;

@FeignClient(name = RoomProxyConstant.SERVICE, url = RoomProxyConstant.URL)
public interface RoomProxy {

	@GetMapping(RoomProxyConstant.GET_ROOM_BY_ROOM_ID)
	public Room getroom(@PathVariable Integer id);

	@GetMapping(RoomProxyConstant.GET_ALL_ROOM)
	public List<Room> getall();
}
