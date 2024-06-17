package com.cabservice.entity;

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
public class RentalCab {

	@Id
	private long rentalCabId;
	private String cabModel;
	private String cabType;
	private String fuelType;
	private String cabImage;
	private int totalSeat;
}
