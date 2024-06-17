package com.paymentservice.externalclass;

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
public class TrainBookingDetails {

	private long trainBookingId;
	private String trainName;
	private long pnr;
	private String origin;
	private String destination;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime departureTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime arrivalTime;
	private String duration;
	private String boardingStation;
	private String nextDay;
	private String seatType;
	private double totalPrice;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime bookedDate;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "trainBookingId", referencedColumnName = "trainBookingId")
	private List<TrainPassenger> trainPassenger;
	private String paymentStatus;
	private String email;
	private String phonenumber;
	private String name;
	private String username;
}
