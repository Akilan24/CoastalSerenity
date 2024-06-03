package com.bookingdetailsservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingdetailsservice.entity.BookingDetails;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {

	boolean existsByBookingid(long bookingId);

	Optional<BookingDetails> findByBookingid(long bookingId);

	Optional<BookingDetails> findByEmail(String email);
	
	void deleteByBookingid(long bookingId);

}
