package com.authservice.proxyentity.cab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cab {

	private long cabId;
	private String cabModel;
	private String cabType;
	private String cabImage;
	private int totalSeat;
	private double cabPrice;
    
}
