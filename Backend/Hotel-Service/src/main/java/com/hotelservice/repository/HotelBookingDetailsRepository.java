package com.hotelservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelservice.entity.HotelBookingDetails;

@Repository
public interface HotelBookingDetailsRepository extends JpaRepository<HotelBookingDetails, Long> {

	boolean existsByHotelBookingId(long bookingId);

	Optional<HotelBookingDetails> findByHotelBookingId(long bookingId);

	Optional<HotelBookingDetails> findByEmail(String email);

	void deleteByHotelBookingId(long bookingId);

}
