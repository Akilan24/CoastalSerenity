package com.busservice.entity;

import java.util.List;

import com.busservice.externalclass.Traveller;

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
