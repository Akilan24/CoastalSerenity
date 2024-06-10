package com.flightservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightservice.entity.FlightBookingDetails;

@Repository
public interface FlightBookingDetailsRepository extends JpaRepository<FlightBookingDetails, Long> {

}
