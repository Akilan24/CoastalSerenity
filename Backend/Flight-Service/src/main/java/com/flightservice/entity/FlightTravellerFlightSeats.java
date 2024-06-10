package com.flightservice.entity;

import java.util.List;

import com.flightservice.externalclass.Traveller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightTravellerFlightSeats {

	List<Traveller> travellers;
	List<FlightSeats> flightSeats;
}
