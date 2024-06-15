package com.authservice.proxyentity.cab;

import java.util.Map;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalPrice {

	private String packageName;
	@ElementCollection
	private Map<String,Double> cabPrice;
}
