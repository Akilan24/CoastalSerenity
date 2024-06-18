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
@Entity
public class CabBookingDetails {

	@Id
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
