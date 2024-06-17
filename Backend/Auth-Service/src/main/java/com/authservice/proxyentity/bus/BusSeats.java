package com.authservice.proxyentity.bus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusSeats {

	private long seatId;
	private String seatNo;
	private String seatType;
	private double seatPrice;
	private boolean bookingStatus;
}
