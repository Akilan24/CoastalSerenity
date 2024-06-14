package com.cabservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cabservice.entity.TripDetails;

@Repository
public interface TripDetailsRepository extends JpaRepository<TripDetails, Integer> {

}
