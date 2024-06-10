package com.busservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busservice.entity.BusSeats;

@Repository
public interface BusSeatsRepository extends JpaRepository<BusSeats, Long> {

}
