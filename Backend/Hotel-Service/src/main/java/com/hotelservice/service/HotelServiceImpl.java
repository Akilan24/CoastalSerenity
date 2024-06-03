package com.hotelservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelservice.entity.Hotel;
import com.hotelservice.exception.HotelDetailsNotFoundException;
import com.hotelservice.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository hrepo;

	@Override
	public List<Hotel> getHotels() throws HotelDetailsNotFoundException {
		List<Hotel> list = hrepo.findAll();
		if (list.isEmpty())
			throw new HotelDetailsNotFoundException("Hotel details are not found");
		return list;
	}

	@Override
	public Set<String> getHotelCityNames() {
		List<Hotel> list = hrepo.findAll();
		if (list.isEmpty())
			throw new HotelDetailsNotFoundException("Hotel details are not found");
		Set<String> city = new HashSet<String>();
		for (Hotel h : list) {
			city.add(h.getCity());
		}
		return city;
	}

	@Override
	public List<String> getHotelNamesByCity(String city) {
		List<Hotel> list = hrepo.findAll().stream().filter(h -> h.getCity().matches(city)).collect(Collectors.toList());
		if (list.isEmpty())
			throw new HotelDetailsNotFoundException("Hotel details are not found");
		List<String> hotel = new ArrayList<String>();
		for (Hotel h : list) {
			hotel.add(h.getHotelName());
		}
		return hotel;
	}

	@Override
	public Hotel gethotelByHotelId(long id) throws HotelDetailsNotFoundException {
		if (hrepo.findByHotelId(id).isPresent()) {
			Hotel h = hrepo.findByHotelId(id).get();
			return h;
		} else
			throw new HotelDetailsNotFoundException("Hotel details are not found");
	}

	@Override
	public Hotel addhotel(Hotel h) throws Exception {
		try {
			long MIN_ID = 100000;
			int count = hrepo.findAll().size();
			h.setHotelId(count == 0 ? MIN_ID : MIN_ID + count);
			return hrepo.save(h);

		} catch (Exception e) {
			throw new Exception("Details are mismatched");
		}
	}

	@Override
	public Hotel updatehotel(Hotel ht) throws HotelDetailsNotFoundException {
		if (hrepo.findByHotelId(ht.getHotelId()).isPresent()) {
			Hotel h = hrepo.findByHotelId(ht.getHotelId()).get();
			h.setHotelName(ht.getHotelName());
			h.setAddress(ht.getAddress());
			h.setCity(ht.getCity());
			h.setDescription(ht.getDescription());
			h.setEmail(ht.getEmail());
			h.setHotelImage(ht.getHotelImage());
			h.setMobile1(ht.getMobile1());
			h.setMobile2(ht.getMobile2());
			h.setWebsite(ht.getWebsite());
			return hrepo.save(h);

		} else
			throw new HotelDetailsNotFoundException("Hotel details are not found");
	}

	@Override
	public String deletehotel(long hid) throws HotelDetailsNotFoundException {
		if (hrepo.findByHotelId(hid).isPresent()) {
			hrepo.deleteByHotelId(hid);
			return "Hotel details are deleted";
		} else
			throw new HotelDetailsNotFoundException("Hotel details are not found");
	}

	@Override
	public Hotel gethotelByHotelName(String hotelName) {
		if (hrepo.findByHotelName(hotelName).isPresent()) {
			return hrepo.findByHotelName(hotelName).get();
		} else
			throw new HotelDetailsNotFoundException("Hotel details are not found");
	}

	@Override
	public List<Hotel> gethotelByCity(String city) {
		if (hrepo.findAllByCity(city).isPresent()) {
			return hrepo.findAllByCity(city).get();
		} else
			throw new HotelDetailsNotFoundException("Hotel details are not found");
	}

}
