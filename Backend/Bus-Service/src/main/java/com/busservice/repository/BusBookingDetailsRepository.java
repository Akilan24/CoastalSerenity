package com.busservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busservice.entity.BusBookingDetails;

@Repository
public interface BusBookingDetailsRepository extends JpaRepository<BusBookingDetails, Long> {

}
