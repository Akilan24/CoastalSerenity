package com.authservice.proxyentity.cab;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(pattern = "HH:mm")
	private LocalTime duration;
	private int distance;
}
