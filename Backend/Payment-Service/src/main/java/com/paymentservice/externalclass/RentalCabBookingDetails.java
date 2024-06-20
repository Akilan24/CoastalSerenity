package com.paymentservice.externalclass;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCabBookingDetails {

	private long rentalCabBookingId;
	private String rentalCabModel;
	private String origin;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime departureTime;
	private String packageType;
	private double rentalCabPrice;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime bookedDate;
	private String paymentStatus;
	private String email;
	private String phonenumber;
	private String name;
	private String username;
}
