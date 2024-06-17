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
	private long pnr;
	private String trainName;
	private String origin;
	private String destination;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime departureTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime arrivalTime;
	private String duration;
	private String departOn;
	private String nextDay;
	@ElementCollection
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Map<String, LocalDateTime> boardingStation;
	@ElementCollection
	private Map<String, Integer> totalSeat;
	@ElementCollection
	private Map<String, Integer> totalCoach;
	@ElementCollection
	private Map<String, Double> seatPrice;
	@ElementCollection
	private Map<String, Integer> trainBookingStatus;
	@ElementCollection
	private Map<String, Integer> trainWaitingListStatus;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "trainId", referencedColumnName = "trainId")
	private List<TrainSeats> trainSeats;
	// 1A-18-1 2A-48-2 3A-64-3 SL-72-6 GN-72-4

}
