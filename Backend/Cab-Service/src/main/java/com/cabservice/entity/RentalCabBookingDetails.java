package com.cabservice.entity;

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
public class RentalCabBookingDetails {

	@Id
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
