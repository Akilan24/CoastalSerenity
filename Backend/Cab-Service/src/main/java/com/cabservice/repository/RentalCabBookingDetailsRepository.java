package com.cabservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cabservice.entity.RentalCabBookingDetails;

@Repository
public interface RentalCabBookingDetailsRepository extends JpaRepository<RentalCabBookingDetails, Long> {

}
