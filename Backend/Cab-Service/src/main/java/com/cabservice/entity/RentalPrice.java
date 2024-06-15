package com.cabservice.entity;

import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
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
	private String packageName;
	@ElementCollection
	private Map<String,Double> cabPrice;
}
