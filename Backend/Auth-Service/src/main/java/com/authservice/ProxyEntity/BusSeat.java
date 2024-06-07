package com.authservice.ProxyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusSeat {

	private long seatId;
	private String seatNo;
	private double seatPrice;
	private String seatType;
    private boolean bookingStatus;
}
