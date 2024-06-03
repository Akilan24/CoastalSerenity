package com.paymentservice.externalclass;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class BookingDetails {

	@Id
	private long bookingid;
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
	private String paymentStatus;

}