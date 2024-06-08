package com.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelservice.externalclass.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
