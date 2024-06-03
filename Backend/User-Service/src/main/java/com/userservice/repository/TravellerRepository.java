package com.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.entity.Traveller;

@Repository
public interface TravellerRepository extends JpaRepository<Traveller, Long> {

}
