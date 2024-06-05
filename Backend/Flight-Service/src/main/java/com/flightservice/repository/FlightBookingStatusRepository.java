package com.flightservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightservice.entity.FlightBookingStatus;

@Repository
public interface FlightBookingStatusRepository extends JpaRepository<FlightBookingStatus, Long>{

}
