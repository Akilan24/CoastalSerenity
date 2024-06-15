package com.cabservice.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCabsRentalPackageDetails {

	List<RentalCab> rentalCabs;
	RentalPackage rentalPackage;
}
