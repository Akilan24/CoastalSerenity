package com.cabservice.entity;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class TripDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tripId;
	private String origin;
	private String Destination;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime duration;
	private int distance;
}
