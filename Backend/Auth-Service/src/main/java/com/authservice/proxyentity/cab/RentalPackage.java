package com.authservice.proxyentity.cab;

import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RentalPackage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rentalPackageId;
	private String From;
	private Map<String,Map<String,Double>> durationPackage;
}
