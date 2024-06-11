package com.trainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainservice.entity.TrainBookingDetails;

@Repository
public interface TrainBookingDetailsRepository extends JpaRepository<TrainBookingDetails, Long> {

}
