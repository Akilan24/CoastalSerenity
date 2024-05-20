package com.bookingdetailsservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingdetailsservice.entity.BookingDetails;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {

	boolean existsByBookingId(long bookingId);
	Optional<BookingDetails> findByBookingId(long bookingId);
	void deleteByBookingId(long bookingId);

}
