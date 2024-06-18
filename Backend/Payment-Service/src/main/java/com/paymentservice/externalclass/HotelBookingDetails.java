package com.paymentservice.externalclass;

import java.time.LocalDateTime;
import java.util.Date;
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
public class HotelBookingDetails {


	private long bookingId;
	private String name;
	private int roomno;
	private String hotelname;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date booked_from;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date booked_to;
	private double amount;
	private String email;
	private String phonenumber;
	private String address;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime bookedDate;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bookingid", referencedColumnName = "bookingid")
	private List<HotelGuest> hotelGuest;
	private String paymentStatus;
	private String username;

}