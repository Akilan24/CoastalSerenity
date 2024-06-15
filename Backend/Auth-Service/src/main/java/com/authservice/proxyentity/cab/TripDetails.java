package com.authservice.proxyentity.cab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDetails {
	
    private int tripId;
	private String origin;
	private String Destination;
	private String duration;
	private int distance;
}
