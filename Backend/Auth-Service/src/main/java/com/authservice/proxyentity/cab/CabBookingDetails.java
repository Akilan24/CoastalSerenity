package com.authservice.proxyentity.cab;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabBookingDetails {

	private long cabBookingId;
	private String cabModel;
	private String origin;
	private String destination;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime departureTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime returnTime;
	private String duration;
	private String journeyType;
	private double cabPrice;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime bookedDate;
	private String paymentStatus;
	private String email;
	private String phonenumber;
	private String name;
	private String username;
}
