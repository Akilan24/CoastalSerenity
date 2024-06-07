package com.authservice.ProxyEntity;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

	private long flightId;
	private String flightModel;
	private String airline;
	private String origin;
	private String destination;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime departureTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime arrivalTime;
	private String duration;
	private String airlineLogo;
	private String stopOver;
	private String nextDay;
	@ElementCollection
	private Map<String, Integer> totalSeat;
	@ElementCollection
	private Map<String, Double> seatPrice;
	@ElementCollection
	private Map<String, Integer> flightBookingStatus;
}