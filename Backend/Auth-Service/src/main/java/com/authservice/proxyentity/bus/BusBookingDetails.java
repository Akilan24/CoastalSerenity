package com.authservice.proxyentity.bus;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusBookingDetails {

	private long busBookingId;
	private String busModel;
	private String busCompany;
	private String origin;
	private String destination;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime departureTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime arrivalTime;
	private String duration;
	private String pickUpPoint;
	private String dropPoint;
	private String route;
	private String nextDay;
	private double totalPrice;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime bookedDate;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "busBookingId", referencedColumnName = "busBookingId")
	private List<BusPassenger> busPassenger;
	private String paymentStatus;
	private String email;
	private String phonenumber;
	private String name;
	private String username;
}
