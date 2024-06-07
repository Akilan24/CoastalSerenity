package com.bookingdetailsservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingdetailsservice.entity.BookingDetails;
import com.bookingdetailsservice.entity.HotelGuest;
import com.bookingdetailsservice.entity.HotelRooms;
import com.bookingdetailsservice.exception.BookingDetailsNotFoundException;
import com.bookingdetailsservice.externalclass.Hotel;
import com.bookingdetailsservice.externalclass.Registration;
import com.bookingdetailsservice.externalclass.Room;
import com.bookingdetailsservice.proxy.RoomProxy;
import com.bookingdetailsservice.proxy.UserProxy;
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
	UserProxy uproxy;
	@Autowired
	HotelRepository hrepo;

	@Override
	public BookingDetails BookRoom(String username, BookingDetails bookingdetails) {
		System.out.println(bookingdetails.getRoomno());
		long MIN_id = 100000;
		int count = bookingrepo.findAll().size();
		bookingdetails.setBookingid(count == 0 ? MIN_id : MIN_id + count);
		Registration user = uproxy.showUserByUserName(username).getBody();
		bookingdetails.setName(user.getName());
		bookingdetails.setEmail(user.getEmail());
		bookingdetails.setPhonenumber(user.getMobile());
		long daysBetween = 0;
		daysBetween = DateUtils.daysBetween(bookingdetails.getBooked_from(), bookingdetails.getBooked_to());
		daysBetween = daysBetween == 0 ? 1 : daysBetween;
		Hotel hotel = hrepo.findAll().stream()
				.filter(h -> h.getHotelName().equalsIgnoreCase(bookingdetails.getHotelname()))
				.collect(Collectors.toList()).get(0);
		double amt = hotel.getRooms().stream().filter(r -> r.getRoom_no() == bookingdetails.getRoomno())
				.collect(Collectors.toList()).get(0).getRate_per_day();
		bookingdetails.setAddress(hotel.getAddress());
		bookingdetails.setAmount(daysBetween * amt);
		bookingdetails.setPaymentStatus("Payment has to be done");
		return bookingrepo.save(bookingdetails);

	}

	@Override
	public String removeBookingDetails(long bookingid) {
		if (bookingrepo.findByBookingid(bookingid).isPresent()) {
			bookingrepo.deleteByBookingid(bookingid);
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
	public BookingDetails showBookingDetailsbyId(long bookingid) {
		if (bookingrepo.findByBookingid(bookingid).isPresent()) {
			BookingDetails bd = bookingrepo.findByBookingid(bookingid).get();
			return bd;
		} else
			throw new BookingDetailsNotFoundException("Booking details are not found");
	}

	@Override
	public BookingDetails paymentstatuschange(long bookingid) {
		if (bookingrepo.findByBookingid(bookingid).isPresent()) {
			BookingDetails bd = bookingrepo.findByBookingid(bookingid).get();
			bd.setPaymentStatus("Payment done");
			return bookingrepo.save(bd);
		} else
			throw new BookingDetailsNotFoundException("Booking details are not found");

	}

	@Override
	public List<HotelRooms> AvailableRoom(String roomType, String city, Date fromDate, Date toDate) {
		String roomtype = roomType.replace("_", " ");
		List<Hotel> hotels = hrepo.findAll().stream().filter(h -> h.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());

		List<HotelRooms> availableRooms = new ArrayList<>();

		for (Hotel hotel : hotels) {

			List<Room> rooms = hotel.getRooms().stream()
					.filter(r -> r.getRoomtype().equalsIgnoreCase(roomtype)
							&& isRoomAvailable(hotel.getHotelName(), r.getRoom_no(), fromDate, toDate))
					.collect(Collectors.toList());
			if (!rooms.isEmpty()) {
				HotelRooms hotelRooms = new HotelRooms();
				hotelRooms.setAddress(hotel.getAddress());
				hotelRooms.setDescription(hotel.getDescription());
				hotelRooms.setHotelName(hotel.getHotelName());
				hotelRooms.setHotelImage(hotel.getHotelImage());
				hotelRooms.setRooms(rooms);
				availableRooms.add(hotelRooms);
			}

		}

		return availableRooms;
	}

	private boolean isRoomAvailable(String hotelname, int roomno, Date fromDate, Date toDate) {

		for (BookingDetails booking : bookingrepo.findAll()) {
			Date bookedFrom = booking.getBooked_from();
			Date bookedTo = booking.getBooked_to();
			if (booking.getHotelname().equalsIgnoreCase(hotelname) && booking.getRoomno() == roomno
					&& (fromDate.before(bookedTo) || fromDate.equals(bookedTo))
					&& (toDate.after(bookedFrom) || toDate.equals(bookedFrom))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public List<BookingDetails> showBookingDetailsbyUserName(String userName) {
		String email = uproxy.showUserByUserName(userName).getBody().getEmail();
		List<BookingDetails> bd = bookingrepo.findAll().stream().filter(b -> b.getEmail().equalsIgnoreCase(email))
				.collect(Collectors.toList());
		if (!bd.isEmpty()) {
			return bd;
		} else
			throw new BookingDetailsNotFoundException("Booking details are not found");

	}

	@Override
	public BookingDetails addGuest(long bookingId, List<HotelGuest> guest) {
		Optional<BookingDetails> bd= bookingrepo.findByBookingid(bookingId);
		if (!bd.isEmpty()) {
			bd.get().setHotelGuest(guest);
			return bookingrepo.save(bd.get());
		} else
			throw new BookingDetailsNotFoundException("Booking details are not found");

	}

}