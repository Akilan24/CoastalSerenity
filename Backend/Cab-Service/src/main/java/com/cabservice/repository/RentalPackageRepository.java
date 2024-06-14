package com.cabservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cabservice.entity.RentalPackage;

@Repository
public interface RentalPackageRepository extends JpaRepository<RentalPackage, Integer> {

}
