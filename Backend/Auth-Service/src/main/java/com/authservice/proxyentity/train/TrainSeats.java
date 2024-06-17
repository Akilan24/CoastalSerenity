package com.authservice.proxyentity.train;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainSeats {

	private long seatId;
	private String seatNo;
	private String seatType;
	private double seatPrice;
	private String coachNo;
	private boolean bookingStatus;
}
