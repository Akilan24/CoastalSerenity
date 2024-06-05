package com.flightservice.entity;

import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FlightBookingStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seatId;
	@ElementCollection
    private Map<String,Integer> bookingStatus;
}
