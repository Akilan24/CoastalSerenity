package com.hotelservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelservice.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

	Optional<Hotel> findByHotelName(String hotelName);

	Optional<List<Hotel>> findAllByCity(String city);

	Optional<Hotel> findByHotelId(long hotelId);

	void deleteByHotelId(long hotelId);

}
