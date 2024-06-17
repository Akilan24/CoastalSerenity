package com.cabservice.entity;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCabsRentalPackageDetails {

	private List<RentalCab> rentalCabs;
	private Map<String, Double> cabPrice;
}
