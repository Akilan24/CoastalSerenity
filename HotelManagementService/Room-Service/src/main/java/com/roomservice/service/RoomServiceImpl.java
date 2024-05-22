
package com.roomservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roomservice.entity.Hotel;
import com.roomservice.entity.Room;
import com.roomservice.exception.RoomDetailsNotFoundException;
import com.roomservice.repository.HotelRepository;
import com.roomservice.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	RoomRepository rrepo;
	@Autowired
	HotelRepository hrepo;

	public Room addRoomDetails(long hid, Room room) throws Exception {
		try {
			long MIN_ID = 100000;
			int count = rrepo.findAll().size();
			room.setRoomId(count == 0 ? MIN_ID : MIN_ID + count);
			Hotel h = hrepo.findByHotelId(hid).get();
			List<Room> l = h.getRooms();
			l.add(room);
			h.setRooms(l);
			hrepo.save(h);
			return room;
		} catch (Exception e) {
			throw new Exception("Details are mismatched");
		}

	}

	public Room updateRoomDetails(Room room) {
		if (rrepo.findByRoomId(room.getRoomId()).isPresent()) {
			Room roomdetails = rrepo.findByRoomId(room.getRoomId()).get();
			roomdetails.setRoomtype(room.getRoomtype());
			roomdetails.setRate_per_day(room.getRate_per_day());
			return rrepo.save(roomdetails);

		} else
			throw new RoomDetailsNotFoundException("Room details are not found");
	}

	public String removeRoomDetails(long roomId) {
		if (rrepo.findByRoomId(roomId).isPresent()) {
			rrepo.deleteByRoomId(roomId);
			return "Room details are deleted";
		} else
			throw new RoomDetailsNotFoundException("Room details are not found");
	}

	public List<Room> showAllRoomDetails() {

		if (!rrepo.findAll().isEmpty()) {
			return rrepo.findAll();
		} else
			throw new RoomDetailsNotFoundException("Room details are not found");

	}

	public Room showRoomDetailsbyId(long roomId) {
		if (rrepo.findByRoomId(roomId).isPresent()) {
			Room r = rrepo.findByRoomId(roomId).get();
			return r;
		} else
			throw new RoomDetailsNotFoundException("Room details are not found");
	}

	@Override
	public List<Room> showAllRoomDetailsByHotelId(long hid) {
		List<Room> r = hrepo.findByHotelId(hid).get().getRooms();
		if (!r.isEmpty())
			return r;
		else
			throw new RoomDetailsNotFoundException("Room details are not found");

	}

	@Override
	public List<Room> showRoomDetailBytype(String type) {
		List<Room> r = rrepo.findAll().stream().filter(a -> a.getRoomtype().equals(type)).collect(Collectors.toList());
		if (!r.isEmpty()) {
			return r;
		} else
			throw new RoomDetailsNotFoundException("Room details are not found");

	}

	@Override
	public List<Room> showRoomDetailByPrice(double price) {
		List<Room> r = rrepo.findAll().stream().filter(p -> p.getRate_per_day() <= price).collect(Collectors.toList());
		if (!r.isEmpty()) {
			return r;
		} else
			throw new RoomDetailsNotFoundException("Room details are not found");

	}

}
