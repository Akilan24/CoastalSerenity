package com.paymentservice.externalclass;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingDetails {

	private long flightBookingId;
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
	private double totalPrice;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime bookedDate;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "flightBookingId", referencedColumnName = "flightBookingId")
	private List<FlightPassenger> flightPassenger;
	private String paymentStatus;
	private String email;
	private String phonenumber;
	private String name;
	private String username;
}
