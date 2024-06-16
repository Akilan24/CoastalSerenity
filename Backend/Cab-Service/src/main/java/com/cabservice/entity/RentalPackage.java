package com.cabservice.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	private String origin;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rentalPackageId", referencedColumnName = "rentalPackageId")
	private List<RentalPrice> durationPackage;
}
