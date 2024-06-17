package com.authservice.proxyentity.cab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCab {


	private long rentalCabId;
	private String cabModel;
	private String cabType;
	private String fuelType;
	private String cabImage;
	private int totalSeat;
}
