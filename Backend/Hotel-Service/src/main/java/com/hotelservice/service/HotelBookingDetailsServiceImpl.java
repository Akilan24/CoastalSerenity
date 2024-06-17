package com.hotelservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelservice.entity.Hotel;
import com.hotelservice.entity.HotelBookingDetails;
import com.hotelservice.entity.HotelGuest;
import com.hotelservice.entity.HotelRooms;
import com.hotelservice.exception.HotelBookingDetailsNotFoundException;
import com.hotelservice.externalclass.Registration;
import com.hotelservice.externalclass.Room;
import com.hotelservice.proxy.RoomProxy;
import com.hotelservice.proxy.UserProxy;
import com.hotelservice.repository.HotelBookingDetailsRepository;
import com.hotelservice.repository.HotelRepository;
import com.hotelservice.repository.RoomRepository;

@Service
public class HotelBookingDetailsServiceImpl implements HotelBookingDetailsService {

	@Autowired
	HotelBookingDetailsRepository bookingrepo;

	@Autowired
	RoomRepository roomrepo;
	@Autowired
	RoomProxy rproxy;
	@Autowired
	UserProxy uproxy;
	@Autowired
	HotelRepository hrepo;

	@Override
	public HotelBookingDetails BookRoom(String username, HotelBookingDetails bookingdetails) {
		System.out.println(bookingdetails.getRoomno());
		long MIN_id = 100000;
		int count = bookingrepo.findAll().size();
		bookingdetails.setBookingId(count == 0 ? MIN_id : MIN_id + count);
		Registration user = uproxy.showUserByUserName(username).getBody();
		bookingdetails.setName(user.getName());
		bookingdetails.setEmail(user.getEmail());
		bookingdetails.setPhonenumber(user.getMobile());
		bookingdetails.setUsername(username);
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
		bookingdetails.setBookedDate(LocalDateTime.now());
		return bookingrepo.save(bookingdetails);

	}

	@Override
	public String removeBookingDetails(long bookingid) {
		if (bookingrepo.findByBookingid(bookingid).isPresent()) {
			bookingrepo.deleteByBookingid(bookingid);
			return "Booking details are deleted";
		} else
			throw new HotelBookingDetailsNotFoundException("Booking details are not found");
	}

	@Override
	public List<HotelBookingDetails> showAllBookingDetails() {
		List<HotelBookingDetails> list = bookingrepo.findAll();
		if (bookingrepo.findAll().isEmpty())
			throw new HotelBookingDetailsNotFoundException("Booking details are not found");
		return list;
	}

	@Override
	public HotelBookingDetails showBookingDetailsbyId(long bookingid) {
		if (bookingrepo.findByBookingid(bookingid).isPresent()) {
			HotelBookingDetails bd = bookingrepo.findByBookingid(bookingid).get();
			return bd;
		} else
			throw new HotelBookingDetailsNotFoundException("Booking details are not found");
	}

	@Override
	public HotelBookingDetails paymentstatuschange(long bookingid) {
		Optional<HotelBookingDetails> bd = bookingrepo.findByBookingid(bookingid);
		if (bd.isPresent()) {
			bd.get().setPaymentStatus("Payment done");
			return bookingrepo.save(bd.get());
		} else
			throw new HotelBookingDetailsNotFoundException("Booking details are not found");

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

		for (HotelBookingDetails booking : bookingrepo.findAll()) {
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
	public HotelBookingDetails showBookingDetailsbyUserNameAndHotelName(String userName, String hotelName) {
		String email = uproxy.showUserByUserName(userName).getBody().getEmail();
		List<HotelBookingDetails> bd = bookingrepo.findAll().stream().filter(b -> b.getEmail().equalsIgnoreCase(email))
				.filter(b -> b.getHotelname().equalsIgnoreCase(hotelName)).collect(Collectors.toList());
		if (!bd.isEmpty()) {
			return bd.get(0);
		} else
			throw new HotelBookingDetailsNotFoundException("Booking details are not found");

	}

	@Override
	public List<HotelBookingDetails> showBookingDetailsbyUserName(String userName) {
		String email = uproxy.showUserByUserName(userName).getBody().getEmail();
		List<HotelBookingDetails> bd = bookingrepo.findAll().stream().filter(b -> b.getEmail().equalsIgnoreCase(email))
				.sorted((b1, b2) -> b2.getBookedDate().compareTo(b1.getBookedDate())).collect(Collectors.toList());
		if (!bd.isEmpty()) {
			return bd;
		} else
			throw new HotelBookingDetailsNotFoundException("Booking details are not found");

	}

	@Override
	public HotelBookingDetails addGuest(long bookingId, List<HotelGuest> guest) {
		Optional<HotelBookingDetails> bd = bookingrepo.findByBookingid(bookingId);
		if (!bd.isEmpty()) {
			bd.get().setHotelGuest(guest);
			return bookingrepo.save(bd.get());
		} else
			throw new HotelBookingDetailsNotFoundException("Booking details are not found");

	}

	@Override
	public HotelBookingDetails resetStatus(long id) {
		Optional<HotelBookingDetails> bd = bookingrepo.findByBookingid(id);
		if (!bd.isEmpty()) {
			bd.get().setPaymentStatus("Payment Cancelled & Refunded");
			;
			return bookingrepo.save(bd.get());
		} else
			throw new HotelBookingDetailsNotFoundException("Booking details are not found");

	}

}