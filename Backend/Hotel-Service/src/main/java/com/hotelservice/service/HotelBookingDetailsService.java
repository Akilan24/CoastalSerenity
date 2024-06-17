package com.hotelservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotelservice.entity.HotelBookingDetails;
import com.hotelservice.entity.HotelGuest;
import com.hotelservice.entity.HotelRooms;

@Service
public interface HotelBookingDetailsService {

	public HotelBookingDetails BookRoom(String username, HotelBookingDetails bookingdetails);

	public String removeBookingDetails(long bookingId);

	public List<HotelBookingDetails> showAllBookingDetails();

	public HotelBookingDetails showBookingDetailsbyId(long bookingId);

	public HotelBookingDetails showBookingDetailsbyUserNameAndHotelName(String userName, String hotelName);

	public List<HotelBookingDetails> showBookingDetailsbyUserName(String userName);

	public HotelBookingDetails paymentstatuschange(long bookingId);

	public HotelBookingDetails addGuest(long bookingId, List<HotelGuest> guest);

	public List<HotelRooms> AvailableRoom(String roomType, String city, Date fromDate, Date ToDate);

	public HotelBookingDetails resetStatus(long id);
}