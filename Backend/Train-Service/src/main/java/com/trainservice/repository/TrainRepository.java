package com.trainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainservice.entity.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

}
