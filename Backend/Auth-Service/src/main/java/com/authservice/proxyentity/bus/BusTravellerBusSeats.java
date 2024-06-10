package com.authservice.proxyentity.bus;

import java.util.List;

import com.authservice.entity.Traveller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusTravellerBusSeats {

	List<Traveller> travellers;
	List<BusSeats> busSeats;
}
