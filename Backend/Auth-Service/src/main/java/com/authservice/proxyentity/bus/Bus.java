package com.authservice.proxyentity.bus;

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
public class Bus {

	private long busId;
	private String busModel;
	private String busCompany;
	private String origin;
	private String destination;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime departureTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime arrivalTime;
	private String duration;
	@ElementCollection
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Map<String, LocalDateTime> pickUpPoint;
	@ElementCollection
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Map<String, LocalDateTime> dropPoint;
	private String route;
	private String nextDay;
	@ElementCollection
	private Map<String, Integer> totalSeat;
	@ElementCollection
	private Map<String, Double> seatPrice;
	@ElementCollection
	private Map<String, Integer> busBookingStatus;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "busId", referencedColumnName = "busId")
	private List<BusSeats> busSeats;
}
