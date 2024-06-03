package com.hotelservice.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hotelservice.entity.Hotel;

@Service
public interface HotelService {

	public List<Hotel> getHotels();

	public Hotel gethotelByHotelId(long id);

	public Hotel gethotelByHotelName(String hotelName);

	public List<Hotel> gethotelByCity(String City);

	public Hotel addhotel(Hotel htl) throws Exception;

	public Hotel updatehotel(Hotel ht);

	public String deletehotel(long id);

	public List<String> getHotelNamesByCity(String city);

	public Set<String> getHotelCityNames();

}
