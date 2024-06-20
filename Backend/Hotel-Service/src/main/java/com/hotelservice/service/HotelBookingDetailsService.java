package com.hotelservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelservice.entity.HotelBookingDetails;
import com.hotelservice.entity.HotelGuest;
import com.hotelservice.entity.HotelRooms;

@Service
public interface HotelBookingDetailsService {

	public HotelBookingDetails bookRoom(String username, HotelBookingDetails bookingdetails);

	public String removeHotelBookingDetailsByHotelBookingId(long bookingId);

	public List<HotelBookingDetails> showAllBookingDetails();

	public HotelBookingDetails showHotelBookingDetailsByHotelBookingId(long bookingId);

	public HotelBookingDetails showHotelBookingDetailsByUserNameAndHotelName(String userName, String hotelName);

	public List<HotelBookingDetails> showHotelBookingDetailsByUserName(String userName);

	public HotelBookingDetails paymentStatusChangeByHotelBookingId(long bookingId);

	public HotelBookingDetails addGuest(long bookingId, List<HotelGuest> guest);

	public List<HotelRooms> AvailableRoom(String roomType, String city, Date fromDate, Date ToDate);

	public HotelBookingDetails cancelPaymentByHotelBookingId(long id);
}