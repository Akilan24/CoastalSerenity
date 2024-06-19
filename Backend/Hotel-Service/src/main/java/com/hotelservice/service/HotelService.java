package com.hotelservice.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hotelservice.entity.Hotel;

@Service
public interface HotelService {

	public List<Hotel> getAllHotel();

	public Hotel getHotelByHotelId(long id);

	public Hotel getHotelByHotelName(String hotelName);

	public List<Hotel> getAllHotelByCity(String City);

	public Hotel addHotel(Hotel htl) throws Exception;

	public Hotel updateHotel(Hotel ht);

	public String deleteHotelByHotelId(long id);

	public List<String> getHotelNamesByCity(String city);

	public Set<String> getAllHotelCityNames();

}
