package com.authservice.ProxyEntity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRooms {
	private String hotelName;
	private String address;
	private String description;
	private String hotelImage;
	private List<Room> rooms;
}
