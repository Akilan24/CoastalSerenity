package com.bookingdetailsservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bookingdetailsservice.entity.BookingDetails;
import com.bookingdetailsservice.entity.HotelRooms;

@Service
public interface BookingDetailsService {

	public BookingDetails BookRoom(String username, BookingDetails bookingdetails);

	public String removeBookingDetails(long bookingId);

	public List<BookingDetails> showAllBookingDetails();

	public BookingDetails showBookingDetailsbyId(long bookingId);

	public BookingDetails paymentstatuschange(long bookingId);

	public List<HotelRooms> AvailableRoom(String roomType, String city, Date fromDate, Date ToDate);
}