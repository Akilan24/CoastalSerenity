package com.roomservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roomservice.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	public Optional<Room> findByRoomId(long roomId);

	public void deleteByRoomId(long roomId);
}
