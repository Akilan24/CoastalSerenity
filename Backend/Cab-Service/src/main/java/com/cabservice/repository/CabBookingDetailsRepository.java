package com.cabservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cabservice.entity.BookingDetails;

@Repository
public interface CabBookingDetailsRepository extends JpaRepository<BookingDetails, Long> {

}
