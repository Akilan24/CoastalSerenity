package com.authservice.proxyentity.flight;

import java.util.List;

import com.authservice.entity.Traveller;

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
