package com.cabservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cab {

	@Id
	private long cabId;
	private String cabModel;
	private String cabType;
	private String cabImage;
	private int totalSeat;
	private double cabPrice;
    
}
