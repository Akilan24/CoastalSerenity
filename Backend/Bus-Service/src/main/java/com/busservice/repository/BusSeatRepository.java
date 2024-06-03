package com.busservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busservice.entity.BusSeat;

@Repository
public interface BusSeatRepository extends JpaRepository<BusSeat, Long>{

}
