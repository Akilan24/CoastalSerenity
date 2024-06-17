package com.cabservice.entity;

import java.util.Map;

import jakarta.persistence.ElementCollection;
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
public class RentalPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rentalPriceId;
	private String packageName;
	@ElementCollection
	private Map<String,Double> cabPrice;
}
