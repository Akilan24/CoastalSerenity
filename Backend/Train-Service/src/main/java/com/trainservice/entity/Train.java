package com.trainservice.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
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
public class Train {

	@Id
	private long trainId;
	private String pnr;
	private String trainName;
	private String origin;
	private String destination;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime departureTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime arrivalTime;
	private String duration;
	private String nextDay;
	@ElementCollection
	private Map<String, Integer> totalSeat;
	@ElementCollection
	private Map<String, Integer> totalCoach;
	@ElementCollection
	private Map<String, Double> seatPrice;
	@ElementCollection
	private Map<String, Integer> trainBookingStatus;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "trainId", referencedColumnName = "trainId")
	private List<TrainSeats> trainSeats;
	// 1a-18-1 2a-48-2 3a-64-3 sl-72-6 gn-72-4

}
