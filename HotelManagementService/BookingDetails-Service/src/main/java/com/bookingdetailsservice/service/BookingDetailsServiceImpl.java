package com.bookingdetailsservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingdetailsservice.entity.BookingDetails;
import com.bookingdetailsservice.exception.BookingDetailsNotFoundException;
import com.bookingdetailsservice.externalclass.Hotel;
import com.bookingdetailsservice.externalclass.Room;
import com.bookingdetailsservice.proxy.RoomProxy;
import com.bookingdetailsservice.repository.BookingDetailsRepository;
import com.bookingdetailsservice.repository.HotelRepository;
import com.bookingdetailsservice.repository.RoomRepository;



@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

	@Autowired
	BookingDetailsRepository bookingrepo;

	@Autowired
	RoomRepository roomrepo;
	@Autowired
	RoomProxy rproxy;
	@Autowired
	HotelRepository hrepo;

	@Override
	public BookingDetails BookRoom(String name, BookingDetails bookingdetails) {

		int count = bookingrepo.findAll().size();
		int MIN_ID = 100000;
		bookingdetails.setBookingId(count == 0 ? MIN_ID: MIN_ID + count );

		bookingdetails.setName(name);
		long daysBetween = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date fromDate = sdf.parse("2024-05-10");
			Date toDate = sdf.parse("2024-05-15");
			daysBetween = DateUtils.daysBetween(fromDate, toDate);
			System.out.println("Days between: " + daysBetween);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		double amt = hrepo.findAll().stream()
				.filter(h -> h.getHotelName().equalsIgnoreCase(bookingdetails.getHotelname()))
				.collect(Collectors.toList()).get(0).getRooms().stream()
				.filter(r -> r.getRoom_no() == bookingdetails.getRoomno()).collect(Collectors.toList()).get(0)
				.getRate_per_day();

		bookingdetails.setAmount(daysBetween * amt);
		bookingdetails.setPaymentStatus("Payment has to be done");
		return bookingrepo.save(bookingdetails);

	}

	@Override
	public String removeBookingDetails(long bookingId) {
		if (bookingrepo.findByBookingId(bookingId).isPresent()) {
			bookingrepo.deleteByBookingId(bookingId);
			return "Booking details are deleted";
		} else
			throw new BookingDetailsNotFoundException("Booking details are not found");
	}

	@Override
	public List<BookingDetails> showAllBookingDetails() {
		List<BookingDetails> list = bookingrepo.findAll();
		if (bookingrepo.findAll().isEmpty())
			throw new BookingDetailsNotFoundException("Booking details are not found");
		return list;
	}

	@Override
	public BookingDetails showBookingDetailsbyId(long bookingId) {
		if (bookingrepo.findByBookingId(bookingId).isPresent()) {
			BookingDetails bd = bookingrepo.findByBookingId(bookingId).get();
			return bd;
		} else
			throw new BookingDetailsNotFoundException("Booking details are not found");
	}

	@Override
	public BookingDetails paymentstatuschange(long bookingId) {
		if (bookingrepo.findByBookingId(bookingId).isPresent()) {
			BookingDetails bd = bookingrepo.findByBookingId(bookingId).get();
			bd.setPaymentStatus("Payment done");
			return bookingrepo.save(bd);
		} else
			throw new BookingDetailsNotFoundException("Booking details are not found");

	}

	@Override
	public List<Room> AvailableRoom(String roomType, String city, Date fromDate, Date toDate) {
		List<Hotel> hotels = hrepo.findAll().stream().filter(h -> h.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());

		List<Room> availableRooms = new ArrayList<>();

		for (Hotel hotel : hotels) {
			List<Room> rooms = roomrepo
					.findAll().stream().filter(r -> r.getHotel().getHotelId() == hotel.getHotelId()
							&& r.getRoomtype().equalsIgnoreCase(roomType) && isRoomAvailable(r, fromDate, toDate))
					.collect(Collectors.toList());
			availableRooms.addAll(rooms);
		}

		return availableRooms;
	}

	private boolean isRoomAvailable(Room room, Date fromDate, Date toDate) {
		for (BookingDetails booking : bookingrepo.findAll()) {
			Date bookedFrom = booking.getBooked_from();
			Date bookedTo = booking.getBooked_to();

			if ((fromDate.before(bookedTo) || fromDate.equals(bookedTo))
					&& (toDate.after(bookedFrom) || toDate.equals(bookedFrom))) {
				return false;
			}
		}

		return true;
	}

}