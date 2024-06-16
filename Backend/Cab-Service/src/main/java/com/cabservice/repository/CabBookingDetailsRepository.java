package com.cabservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cabservice.entity.CabBookingDetails;

@Repository
public interface CabBookingDetailsRepository extends JpaRepository<CabBookingDetails, Long> {

}
