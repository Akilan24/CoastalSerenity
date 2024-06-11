package com.authservice.proxyentity.train;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {

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
}
