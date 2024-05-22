package com.roomservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roomservice.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	Optional<Hotel> findByHotelId(long hotelId);
}
