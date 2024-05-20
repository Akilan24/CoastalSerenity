package com.bookingdetailsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingdetailsservice.externalclass.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
