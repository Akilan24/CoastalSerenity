package com.busservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busservice.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long>{

}
