package com.authservice.ProxyEntity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

	private long paymentid;
	private long bookingid;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date paymentDate;
	private String username;
	private double amount;
	private String paymentStatus;

}
