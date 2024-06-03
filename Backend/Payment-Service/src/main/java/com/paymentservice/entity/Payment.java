package com.paymentservice.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

	@Id
	private long paymentid;
	private long bookingid;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date paymentDate;
	private String username;
	private double amount;
	private String paymentStatus;

}
