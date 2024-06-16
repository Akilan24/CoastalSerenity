package com.authservice.proxyentity.cab;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalPackage {

	private int rentalPackageId;
	private String origin;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rentalPackageId", referencedColumnName = "rentalPackageId")
	private List<RentalPrice> durationPackage;
}
