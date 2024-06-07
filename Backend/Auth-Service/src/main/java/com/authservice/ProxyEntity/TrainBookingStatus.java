package com.authservice.ProxyEntity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainBookingStatus {

	private long seatId;
    private Map<String,Integer> bookingStatus;
}
