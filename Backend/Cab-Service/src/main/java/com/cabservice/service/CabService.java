package com.cabservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cabservice.entity.Cab;

@Service
public interface CabService {
	List<Cab> getAllCabs();

	Optional<Cab> getCabById(long id);

	Cab saveCab(Cab cab);

	Cab updateCab(long id, Cab cab);

	String deleteCab(long id);
	
	Cab resetStatus(long id);
}
