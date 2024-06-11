package com.trainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainservice.entity.TrainSeats;

@Repository
public interface TrainSeatsRepository extends JpaRepository<TrainSeats, Long> {

}
