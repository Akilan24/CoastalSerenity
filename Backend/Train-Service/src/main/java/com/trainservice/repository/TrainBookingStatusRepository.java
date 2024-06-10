package com.trainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainservice.entity.TrainBookingStatus;

@Repository
public interface TrainBookingStatusRepository extends JpaRepository<TrainBookingStatus, Long> {

}
